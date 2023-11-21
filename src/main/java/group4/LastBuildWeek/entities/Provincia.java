package group4.LastBuildWeek.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "provincie")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provincia_id")
    private long provinciaId;
    private String sigla;
    private String provincia;
    private String regione;

//    @OneToMany(mappedBy = "provincia", fetch = FetchType.EAGER)
//    private List<Comune> comuni;

    public Provincia(String sigla, String provincia, String regione) {
        this.sigla = sigla;
        this.provincia = provincia;
        this.regione = regione;
    }
}
