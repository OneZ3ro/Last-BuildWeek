package group4.LastBuildWeek.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record NuovaFatturaDTO(
        @NotEmpty(message = "Il campo è obbligatorio!")
        String numeroFattura,
        @NotNull(message = "Il campo è obbligatorio!")
        double importoFattura,
        @NotNull(message = "Il campo è obbligatorio!")
        long clienteId) {}
