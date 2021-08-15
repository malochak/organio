package dev.malkon.events.controller;

import dev.malkon.events.domain.Event;
import dev.malkon.events.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static dev.malkon.events.constants.RequestPathConstants.API_EVENT;
import static dev.malkon.events.constants.RequestPathConstants.ID_PATH;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(API_EVENT)
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    @ResponseBody
    @ResponseStatus(CREATED)
    public Event create(@Valid @RequestBody Event event, BindingResult bindingResult) {
        return eventService.save(event, bindingResult);
    }

    @PostMapping(ID_PATH)
    @ResponseBody
    @ResponseStatus(ACCEPTED)
    public Event update(@PathVariable String id, @Valid @RequestBody Event event, BindingResult bindingResult) {
        return eventService.update(id, event, bindingResult);
    }

    @GetMapping(ID_PATH)
    @ResponseBody
    @ResponseStatus(FOUND)
    public Event findById(@PathVariable String id) {
        return eventService.finById(id);
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(FOUND)
    public List<Event> findAll() {
        return eventService.findAll();
    }

}
