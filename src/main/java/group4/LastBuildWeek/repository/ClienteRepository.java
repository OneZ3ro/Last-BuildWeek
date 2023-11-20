package group4.LastBuildWeek.repository;

import group4.LastBuildWeek.entities.Cliente;
import group4.LastBuildWeek.entities.Indirizzi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByNomeContatto(String nome);
    Optional<Cliente> findByFatturaAnnuale(double fatturato);
    Optional<List<Cliente>> findByDataInserimento(LocalDate dataInserimento);
    Optional<List<Cliente>> findByDataUltimoContatto(LocalDate dataUltimoContatto);
    Optional<Cliente> findBySedeLegale(Indirizzi provinciaSedeLegale);
}
