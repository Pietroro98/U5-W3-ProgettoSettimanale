package RomanoPietro.u5w3Progetto.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "events")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String title;
    private String description;
    private String date;
    private String location;
    private int numberOfAviableSeats;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User organizer;

    public Event(String title, String description, String date, String location, int numberOfAviableSeats) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.numberOfAviableSeats = numberOfAviableSeats;

    }
}
