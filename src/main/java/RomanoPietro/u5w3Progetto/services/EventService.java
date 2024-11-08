package RomanoPietro.u5w3Progetto.services;

import RomanoPietro.u5w3Progetto.entities.Event;
import RomanoPietro.u5w3Progetto.exceptions.NotFoundException;
import RomanoPietro.u5w3Progetto.payloads.NewEventDTO;
import RomanoPietro.u5w3Progetto.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;


    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event save(NewEventDTO body) {
        Event newViaggio = new Event(body.title(), body.description(), body.date(), body.location(), body.numberOfAviableSeats());
        return this.eventRepository.save(newViaggio);
    }


    public Event findById(UUID eventID) {
        return eventRepository.findById(eventID).orElseThrow(() -> new NotFoundException(eventID));
    }


}
