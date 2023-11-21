package group4.LastBuildWeek.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import group4.LastBuildWeek.enums.StatoFattura;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "fatture")
@Getter
@ToString
@JsonIgnoreProperties({"cliente"})
public class Fattura {
    @Id
    @GeneratedValue
    private long id;
    private String numeroFattura;
    private LocalDate dataFattura;
    private double importoFattura;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @Enumerated(EnumType.STRING)
    private StatoFattura statoFattura;


    public void setDataFattura(LocalDate dataFattura) {
        this.dataFattura = dataFattura;
    }

    public void setNumeroFattura(String numeroFattura) {
        this.numeroFattura = numeroFattura;
    }


    public void setImportoFattura(double importoFattura) {
        this.importoFattura = importoFattura;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setStatoFattura(StatoFattura statoFattura) {
        this.statoFattura = statoFattura;
    }
}
