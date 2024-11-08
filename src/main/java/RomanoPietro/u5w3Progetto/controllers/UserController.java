package RomanoPietro.u5w3Progetto.controllers;


import RomanoPietro.u5w3Progetto.entities.User;
import RomanoPietro.u5w3Progetto.exceptions.BadRequestException;
import RomanoPietro.u5w3Progetto.payloads.NewUserDTO;
import RomanoPietro.u5w3Progetto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;


/*
*****************************CRUD******************************
*
1. GET http://localhost:3005/users
2. POST http://localhost:3005/users (+ req.body) --> 201
3. GET http://localhost:3005/users/{userId}
*
* **************************************************************
*/

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    //1. GET http://localhost:3005/users
    @GetMapping
    public Page<User> findAll(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "id") String sortBy) {
        return this.userService.findAll(page, size, sortBy);
    }

    //2. POST http://localhost:3005/users (+ req.body) --> 201
    @PostMapping
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

    //3. GET http://localhost:3005/users/{userId}
    @GetMapping("/{userId}")
    public User findById(@PathVariable UUID userId) {
        return this.userService.findById(userId);
    }

}
