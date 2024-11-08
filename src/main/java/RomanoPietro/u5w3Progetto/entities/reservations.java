package RomanoPietro.u5w3Progetto.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class reservations {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public reservations(User user, Event event) {
        this.user = user;
        this.event = event;
    }
}
