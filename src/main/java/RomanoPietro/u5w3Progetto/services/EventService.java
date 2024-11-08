package RomanoPietro.u5w3Progetto.services;

import RomanoPietro.u5w3Progetto.entities.Event;
import RomanoPietro.u5w3Progetto.exceptions.BadRequestException;
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

    public Event findByIdAndUpdate(UUID eventId, NewEventDTO body) {
        Event found = this.findById(eventId);

        if(found.getDate().equals(body.date())
                && found.getLocation().equals(body.location()))
            this.eventRepository.findByDataAndLocation(body.date(), body.location());

        found.setLocation(body.location());
        found.setDate(body.date());

        if (body.numberOfAviableSeats() > 10){
            throw new BadRequestException("Non puoi aggiungere altri posti a questo evento");
        }

        return this.eventRepository.save(found);
    }

}
