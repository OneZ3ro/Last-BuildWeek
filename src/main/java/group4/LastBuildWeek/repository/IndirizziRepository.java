package group4.LastBuildWeek.repository;

import group4.LastBuildWeek.entities.Indirizzi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndirizziRepository extends JpaRepository<Indirizzi, Long> {
}
