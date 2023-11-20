package group4.LastBuildWeek.payloads.entities;

import jakarta.validation.constraints.NotEmpty;

public record NewIndirizziDTO(@NotEmpty(message = "La via è un campo obbligatorio!")
                              String via,
                              @NotEmpty(message = "Il civico è un campo obbligatorio!")
                              String civico,
                              @NotEmpty(message = "La località è un campo obbligatorio!")
                              String località,
                              @NotEmpty(message = "Il cap è un campo obbligatorio!")
                              String cap,
                              @NotEmpty(message = "Il comune è un campo obbligatorio!")
                              String comune){}
