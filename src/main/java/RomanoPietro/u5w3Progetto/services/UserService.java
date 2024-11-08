package RomanoPietro.u5w3Progetto.services;

import RomanoPietro.u5w3Progetto.entities.User;
import RomanoPietro.u5w3Progetto.exceptions.BadRequestException;
import RomanoPietro.u5w3Progetto.exceptions.NotFoundException;
import RomanoPietro.u5w3Progetto.payloads.NewUserDTO;
import RomanoPietro.u5w3Progetto.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Page<User> findAll(int page, int size, String sortBy) {
        if(size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.userRepository.findAll(pageable);

    }

    public User save(NewUserDTO body) {
        this.userRepository.findByEmail(body.email()).ifPresent(
                user -> {
                    throw new BadRequestException("Email" + body.email() + "giá in uso");
                }
        );
        User newUser = new User(body.username(), body.name(), body.surname(), body.email(), body.password(), body.role());
        return this.userRepository.save(newUser);

    }

    public User findById(UUID userId) {
        return this.userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException(userId));
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(
                ()-> new NotFoundException("L'utente con email" + email + "non é stato trovato!"));
    }
}
