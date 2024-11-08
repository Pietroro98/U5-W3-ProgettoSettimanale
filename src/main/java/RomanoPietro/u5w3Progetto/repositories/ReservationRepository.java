package RomanoPietro.u5w3Progetto.repositories;


import RomanoPietro.u5w3Progetto.entities.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservations, UUID> {
}
