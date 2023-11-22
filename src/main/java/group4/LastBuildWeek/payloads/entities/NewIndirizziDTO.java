package group4.LastBuildWeek.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewIndirizziDTO(@NotEmpty(message = "La via è un campo obbligatorio!")
                              String via,
                              @NotEmpty(message = "Il civico è un campo obbligatorio!")
                              String civico,
                              @NotEmpty(message = "Il cap è un campo obbligatorio!")
                              String cap,
                              @NotEmpty(message = "La provincia è un campo obbligatorio!")
                              String provincia,
                              @NotNull(message = "L'id del comune è un campo obbligatorio!")
                              long comuneId) {
}
