package group4.LastBuildWeek.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Indirizzi {
    @Id
    @GeneratedValue
    private long id;

    private String via;
    private String civico;
    private String località;
}
