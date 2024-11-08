package RomanoPietro.u5w3Progetto.controllers;


import RomanoPietro.u5w3Progetto.entities.User;
import RomanoPietro.u5w3Progetto.exceptions.BadRequestException;
import RomanoPietro.u5w3Progetto.payloads.NewUserDTO;
import RomanoPietro.u5w3Progetto.payloads.UserLoginDTO;
import RomanoPietro.u5w3Progetto.payloads.UserLoginResponseDTO;
import RomanoPietro.u5w3Progetto.services.LoginService;
import RomanoPietro.u5w3Progetto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        return new UserLoginResponseDTO(this.loginService.checkCredentialsAndGenerateToken(body));
    }

    //2. POST http://localhost:3005/users (+ req.body) --> 201
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Validated NewUserDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            String message = validationResult
                    .getAllErrors()
                    .stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            throw new BadRequestException("ci sono stati errori nel payload!" + message);
        }
        return this.userService.save(body);
    }


}
