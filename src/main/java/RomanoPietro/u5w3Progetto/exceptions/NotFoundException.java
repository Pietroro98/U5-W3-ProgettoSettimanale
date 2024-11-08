package RomanoPietro.u5w3Progetto.exceptions;


import java.util.UUID;

public class NotFoundException extends RuntimeException{
    public NotFoundException(UUID id) {
        super("Il record con id: " + id + " non é stato trovato");
    }

}
