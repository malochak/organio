package dev.malkon.events.service;

import dev.malkon.events.domain.Event;
import dev.malkon.events.error.domain.RequestSubError;
import dev.malkon.events.error.exception.InvalidRequestBodyException;
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
        throwExceptionIfBodyInvalid(bindingResult);

        var saved = repository.save(event);
        log.info("Saved event inside DB - " + saved);
        return saved;
    }

    public Event finById(String id) {
        var found = repository.findById(id);
        log.info("Found event inside DB - " + found);
        return found.orElseThrow();
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
