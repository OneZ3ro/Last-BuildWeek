package group4.LastBuildWeek.payloads.entities;

import group4.LastBuildWeek.enums.StatoFattura;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public record NuovaFatturaDTO(
        @NotEmpty(message = "Il campo è obbligatorio!")
        String numeroFattura,
        @NotNull(message = "Il campo è obbligatorio!")
        double importoFattura,
        @NotNull(message = "Il campo è obbligatorio!")
        long clienteId,
        LocalDate dataFattura,
        StatoFattura statoFattura
) {}
