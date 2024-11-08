package RomanoPietro.u5w3Progetto.repositories;

import RomanoPietro.u5w3Progetto.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
