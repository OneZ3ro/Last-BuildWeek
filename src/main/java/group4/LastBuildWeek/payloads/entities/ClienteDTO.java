package group4.LastBuildWeek.payloads.entities;

import group4.LastBuildWeek.enums.TipoCliente;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ClienteDTO(
        TipoCliente tipoCliente,
        @NotEmpty(message = "La ragione sociale è obbligatoria")
        @Size(min = 4, message = "La ragione sociale deve avere un minimo di 4 caratteri")
        String ragioneSociale,
        @NotEmpty(message = "La partita iva è obbligatoria")
        @Size(min = 5, message = "La partita iva deve avere un minimo di 4 caratteri")
        String partitaIva,
        @NotEmpty(message = "La email è obbligatoria")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida")
        String email,

        LocalDate dataInserimento,
        LocalDate dataUltimoContatto,
        @NotNull(message = "La fattura annuale è obbligatoria")
        double fatturaAnnuale,
        @NotEmpty(message = "La pec è obbligatoria")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "La pec inserita non è valida")
        String pec,
        @NotEmpty(message = "Il telefono è obbligatorio")
        @Size(min = 10, max = 13, message = "Il telefono deve avere tra 10 e 13 caratteri")
        String telefono,
        @NotEmpty(message = "L'email di contatto è obbligatoria")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email di contatto inserita non è valida")
        String emailContatto,
        @NotEmpty(message = "Il nome è obbligatorio")
        @Size(min = 2, max = 20, message = "Il nome deve avere tra 2 e 20 caratteri")
        String nomeContatto,
        @NotEmpty(message = "Il cognome è obbligatorio")
        @Size(min = 2, max = 30, message = "Il nome deve avere tra 2 e 30 caratteri")
        String cognomeContatto,
        @NotEmpty(message = "Il telefono di contatto è obbligatorio")
        @Size(min = 10, max = 13, message = "Il telefono di contatto deve avere tra 10 e 13 caratteri")
        String telefonoContatto,
        String logoAziendale,
        @NotNull(message = "devi passare la sede legale id")
        long sedeLegaleId,
        long sedeOperativaId
) {
}
