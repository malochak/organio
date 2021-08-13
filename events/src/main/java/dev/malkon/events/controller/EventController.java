package dev.malkon.events.controller;

import dev.malkon.events.domain.Event;
import dev.malkon.events.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static dev.malkon.events.constants.RequestPathConstants.API_EVENT;
import static dev.malkon.events.constants.RequestPathConstants.ID_PATH;
import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping(API_EVENT)
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<Event> createOrUpdate(@RequestBody @Valid Event event, BindingResult bindingResult) {
        return created(eventService.save(event, bindingResult).toURI()).build();
    }

    @GetMapping(ID_PATH)
    public ResponseEntity<Event> findById(@PathVariable String id) {
        return new ResponseEntity<>(eventService.finById(id), HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Event>> findAll() {
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.FOUND);
    }

}
