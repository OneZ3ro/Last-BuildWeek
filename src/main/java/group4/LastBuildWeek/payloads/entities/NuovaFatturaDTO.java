package group4.LastBuildWeek.payloads.entities;

import group4.LastBuildWeek.entities.Cliente;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record NuovaFatturaDTO(
        @NotEmpty(message = "Il campo è obbligatorio!")
        String numeroFattura,
        @NotEmpty(message = "Il campo è obbligatorio!")
        double importoFattura,
        @NotEmpty(message = "Il campo è obbligatorio!")
        Cliente cliente) {}
