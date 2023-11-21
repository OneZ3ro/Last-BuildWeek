package group4.LastBuildWeek.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comuni")
@NoArgsConstructor
@Getter
@Setter
public class Comune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comune_id")
    private long comuneId;
    private String provinciaIdCsv;
    private String comuneIdCsv;
    private String nomeComune;
    private String nomeProvincia;
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "provincia_id", nullable = false)
//    private Provincia provincia;

    public Comune(String provinciaIdCsv, String comuneIdCsv, String nomeComune, String nomeProvincia) {
        this.provinciaIdCsv = provinciaIdCsv;
        this.comuneIdCsv = comuneIdCsv;
        this.nomeComune = nomeComune;
        this.nomeProvincia = nomeProvincia;
    }
}
