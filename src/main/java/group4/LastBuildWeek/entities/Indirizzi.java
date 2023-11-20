package group4.LastBuildWeek.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    private String località;
    private String cap;
    private String comune;
}
