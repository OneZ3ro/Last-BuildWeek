package group4.LastBuildWeek.repository;

import group4.LastBuildWeek.entities.Indirizzi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IndirizziRepository extends JpaRepository<Indirizzi, Long> {
    Optional<List<Indirizzi>> findByProvincia(String provincia);
}
