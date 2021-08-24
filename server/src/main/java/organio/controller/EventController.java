package organio.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import organio.domain.Event;
import organio.service.EventService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static organio.constants.RequestPathConstants.API_EVENT;

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

    @PostMapping("/{id}")
    @ResponseBody
    @ResponseStatus(ACCEPTED)
    public Event update(@PathVariable String id, @Valid @RequestBody Event event, BindingResult bindingResult) {
        return eventService.update(id, event, bindingResult);
    }

    @GetMapping("/{id}")
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
