package RomanoPietro.u5w3Progetto.controllers;



import RomanoPietro.u5w3Progetto.entities.Event;
import RomanoPietro.u5w3Progetto.entities.User;
import RomanoPietro.u5w3Progetto.exceptions.BadRequestException;
import RomanoPietro.u5w3Progetto.payloads.NewEventDTO;
import RomanoPietro.u5w3Progetto.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    //1. GET http://localhost:3005/events
    @GetMapping
    public List<Event> findAll(){
        return this.eventService.findAll();
    }

    //2. POST http://localhost:3005/events (+ req.body) --> 201
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Event save(@RequestBody @Validated NewEventDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult
                    .getAllErrors()
                    .stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            throw new BadRequestException("ci sono stati errori nel payload!" + message);
        }
        return this.eventService.save(body);
    }

    //3. GET http://localhost:3005/events/{eventIDId}
    @GetMapping("/{eventId}")
    public Event findById(@PathVariable UUID eventId) {
        return this.eventService.findById(eventId);
    }

    // 4. PUT http://localhost:3005/events/{eventId} (+ req.body)
    @PutMapping("/{eventId}")
    public Event findByIdAndUpdate(@PathVariable UUID eventId, @RequestBody NewEventDTO body) {
        return this.eventService.findByIdAndUpdate(eventId, body);
    }

}
