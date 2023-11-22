package group4.LastBuildWeek.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Indirizzi {
    @Id
    @GeneratedValue
    private long id;
    private String via;
    private String civico;
    private String cap;
    private String provincia;

    @ManyToOne
    @JoinColumn(name = "comune_id")
    private Comune comuneId;
}
