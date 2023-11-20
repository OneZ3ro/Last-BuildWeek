package group4.LastBuildWeek.payloads;

import jakarta.validation.constraints.NotEmpty;

public record NewIndirizziDTO(@NotEmpty(message = "La via è un campo obbligatorio!")
                              String via,
                              @NotEmpty(message = "Il civico è un campo obbligatorio!")
                              String civico,
                              @NotEmpty(message = "La località è un campo obbligatorio!")
                              String località) {
}
