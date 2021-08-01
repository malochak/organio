package dev.malkon.events.service;

import dev.malkon.events.domain.Event;
import dev.malkon.events.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventService {

    private final EventRepository repository;

    public Event add(Event event) {
        var saved = repository.save(event);
        log.info("Saved event inside DB - " + saved);
        return saved;
    }
}
