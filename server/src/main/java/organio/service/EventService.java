package organio.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import organio.domain.Event;
import organio.error.exception.InvalidRequestBodyException;
import organio.error.exception.RecordCreationException;
import organio.error.exception.RecordNotFoundException;
import organio.repository.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventService {

    private final EventRepository repository;
    private final BodyValidationService bodyValidationService;
    private static final String INVALID_EVENT_BODY = "Event Request Body is INVALID";

    public Event save(Event event, BindingResult bindingResult) {
        if (event.getId() != null) {
            log.error("Cannot create Event with predefined ID");
            throw new RecordCreationException("Cannot create Event with predefined ID");
        }

        bodyValidationService.checkBodyAndThrowIfNotValid(bindingResult, INVALID_EVENT_BODY);

        var saved = repository.save(event);
        log.info("Saved event inside DB - " + saved);
        return saved;
    }

    public Event update(String id, Event event, BindingResult bindingResult) {
        bodyValidationService.checkBodyAndThrowIfNotValid(bindingResult, INVALID_EVENT_BODY);
        String eventId = event.getId();

        if (eventId != null && eventId.equals(id)) {
            var existingEvent = repository.findById(id);
            return updateIfPresentOrElseThrow(existingEvent, event);
        } else {
            log.error("Event id ({}) from path don't match id in body ({})", id, eventId);
            throw new InvalidRequestBodyException("Event id from path don't match id in body");
        }
    }

    public Event updateIfPresentOrElseThrow(Optional<Event> existingEvent, Event updatedEvent) {
        if (existingEvent.isPresent()) {
            var found = existingEvent.get();
            log.info("Event Update - event found inside DB - {}", found);
            return repository.save(updatedEvent);
        } else {
            String id = updatedEvent.getId();
            log.error("Event Update - event with id {} not found inside DB", id);
            throw new RecordNotFoundException("Event update failed, cannot find event with id " + id, id);
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
}
