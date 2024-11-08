package RomanoPietro.u5w3Progetto.payloads;

public record NewEventDTO(
         String title,
         String description,
         String date,
         String location,
        int numberOfAviableSeats
) {
}
