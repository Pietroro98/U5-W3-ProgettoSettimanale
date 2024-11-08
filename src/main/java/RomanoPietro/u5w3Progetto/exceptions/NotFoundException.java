package RomanoPietro.u5w3Progetto.exceptions;



public class NotFoundException extends RuntimeException{
    public NotFoundException(long id) {
        super("Il record con id: " + id + " non é stato trovato");
    }

    public NotFoundException(String msg) {
        super(msg);
    }

}
