package RomanoPietro.u5w3Progetto.services;

import RomanoPietro.u5w3Progetto.entities.User;
import RomanoPietro.u5w3Progetto.exceptions.UnauthorizedException;
import RomanoPietro.u5w3Progetto.payloads.UserLoginDTO;
import RomanoPietro.u5w3Progetto.tools.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserService userService;

    @Autowired
    private JWT jwt;

    public String checkCredentialsAndGenerateToken(UserLoginDTO body) {
        User found = this.userService.findByEmail(body.email());

        if (found.getPassword().equals(body.password())) {
            String accessToken = jwt.createToken(found);
            return accessToken;
        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }
    }

}
