package group4.LastBuildWeek.payloads;

import jakarta.validation.constraints.NotEmpty;

public record UtenteRegistrationDTO(
        @NotEmpty(message = "Lo username è un campo obbligatorio!")
        String username,
        @NotEmpty(message = "L'email è un campo obbligatorio!")
        String email,
        @NotEmpty(message = "La password è un campo obbligatorio!")
        String password,
        @NotEmpty(message = "Il nome è un campo obbligatorio!")
        String nome,
        @NotEmpty(message = "Il cognome è un campo obbligatorio!")
        String cognome
){}

