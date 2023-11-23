package group4.LastBuildWeek.repository;

import group4.LastBuildWeek.entities.Cliente;
import group4.LastBuildWeek.entities.Fattura;
import group4.LastBuildWeek.enums.StatoFattura;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FattureRepository extends JpaRepository <Fattura, Integer> {
    Optional<Fattura> findByNumeroFattura(String numeroFattura);

    Optional<Page<Fattura>> findByCliente(Cliente cliente, Pageable pageable );

    Optional<Page<Fattura>> findByStatoFattura(StatoFattura statoFattura, Pageable pageable);

    Optional<Page<Fattura>> findByDataFattura(LocalDate data, Pageable pageable);

    @Query("SELECT f FROM Fattura f WHERE EXTRACT(YEAR FROM f.dataFattura) = :year")
    Optional<Page<Fattura>> findFattureByYear(@Param("year") int year, Pageable pageable);

//    Optional<List<Fattura>> findAllByImportoFatturaGreaterThanEqualAndLessThanEqual(double importoFattura1, double importoFattura2);

    Optional<Page<Fattura>> findAllByImportoFatturaBetween(double importoFattura1, double importoFattura2, Pageable pageable);
}
