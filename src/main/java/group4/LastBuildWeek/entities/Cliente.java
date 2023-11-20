package group4.LastBuildWeek.entities;

import group4.LastBuildWeek.enums.TipoCliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "clienti")
@Getter
@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private long clienteId;
    private String ragioneSociale;
    private String partitaIva;
    private String email;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private double fatturaAnnuale;
    private String pec;
    private String telefono;
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private String telefonoContatto;
    private String logoAziendale;
    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;
    @OneToMany(mappedBy = "cliente")
    private List<Fattura> listaFatture;
    @OneToOne
    @JoinColumn(name = "indirizzo_sede_legale_id")
    private Indirizzi sedeLegale;
    @OneToOne
    @JoinColumn(name = "indirizzo_sede_operativa_id")
    private Indirizzi sedeOperativa;
}
