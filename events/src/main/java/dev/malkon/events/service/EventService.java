package dev.malkon.events.service;

import dev.malkon.events.domain.Event;
import dev.malkon.events.error.domain.RequestSubError;
import dev.malkon.events.error.exception.InvalidRequestBodyException;
import dev.malkon.events.error.exception.RecordCreationException;
import dev.malkon.events.error.exception.RecordNotFoundException;
import dev.malkon.events.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventService {

    private final EventRepository repository;
    private final ErrorMappingService errorMappingService;


    public Event save(Event event, BindingResult bindingResult) {
        if (event.getId() != null) {
            log.error("Cannot create Event with predefined ID");
            throw new RecordCreationException("Cannot create Event with predefined ID");
        }

        throwExceptionIfBodyInvalid(bindingResult);

        var saved = repository.save(event);
        log.info("Saved event inside DB - " + saved);
        return saved;
    }

    public Event update(String id, Event event, BindingResult bindingResult) {
        throwExceptionIfBodyInvalid(bindingResult);
        String eventId = event.getId();

        if (eventId != null && eventId.equals(id)) {
            var foundOpt = repository.findById(id);

            // Todo - extract to another method
            if (foundOpt.isPresent()) {
                var found = foundOpt.get();
                log.info("Event Update - event found inside DB - {}", found);
                return repository.save(event);
            } else {
                log.error("Event Update - event with id {} not found inside DB", id);
                throw new RecordNotFoundException("Event update failed, cannot find event with id " + id, id);
            }
        } else {
            log.error("Event id ({}) from path don't match id in body ({})", id, eventId);
            throw new InvalidRequestBodyException("Event id from path don't match id in body");
        }
    }

    public Event finById(String id) {
        var found = repository.findById(id);

        found.ifPresent(event -> log.info("Found event inside DB - " + event));

        return found.orElseThrow(
                () -> new RecordNotFoundException("Event with id " + id + " has not been found.", id)
        );
    }

    public List<Event> findAll() {
        var found = repository.findAll();
        log.info("Found {} events inside db - {}", found.size(), found);
        return found;
    }

    private void throwExceptionIfBodyInvalid(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<RequestSubError> validationErrors =
                    errorMappingService.mapToValidationErrors(bindingResult);

            log.error("Event Request Body is INVALID - {}", validationErrors);

            throw new InvalidRequestBodyException("Event body is invalid", validationErrors);
        }
    }
}
