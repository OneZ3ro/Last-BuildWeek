package group4.LastBuildWeek.payloads;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record NuovaFatturaDTO(
        @NotEmpty(message = "Il campo è obbligatorio!")
        String numeroFattura,
        @NotEmpty(message = "Il campo è obbligatorio!")
        double importoFattura,
        @NotEmpty(message = "Il campo è obbligatorio!")
        Cliente cliente) {
        public static final Object LocalDate = LocalDate.now;
}
