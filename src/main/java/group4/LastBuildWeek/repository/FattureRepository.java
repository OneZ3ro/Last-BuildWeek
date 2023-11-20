package group4.LastBuildWeek.repository;

import group4.LastBuildWeek.entities.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FattureRepository extends JpaRepository <Fattura, Integer> {
    Optional<Fattura> findByNumeroFattura(String numeroFattura);
}
