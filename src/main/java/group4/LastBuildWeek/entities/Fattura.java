package group4.LastBuildWeek.entities;

import group4.LastBuildWeek.enums.StatoFattura;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "fatture")
@NoArgsConstructor
@Getter
@ToString
public class Fattura {
    @Id
    @GeneratedValue
    private long id;
    private String numeroFattura;
    private Date dataFattura;
    private double importoFattura;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @Enumerated(EnumType.STRING)
    private StatoFattura statoFattura;

    public Fattura(Date dataFattura, double importoFattura, Cliente cliente, StatoFattura statoFattura) {
        this.dataFattura = dataFattura;
        this.importoFattura = importoFattura;
        this.cliente = cliente;
        this.statoFattura = statoFattura;
    }

    public void setDataFattura(Date dataFattura) {
        this.dataFattura = dataFattura;
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
