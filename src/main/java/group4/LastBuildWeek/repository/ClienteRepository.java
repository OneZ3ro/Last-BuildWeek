package group4.LastBuildWeek.repository;

import group4.LastBuildWeek.entities.Cliente;
import group4.LastBuildWeek.entities.Indirizzi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Page<Cliente>> findByNomeContattoContaining(String nomeContatto, Pageable pageable);
    Optional<Cliente> findByFatturaAnnuale(double fatturato);
    Optional<Page<Cliente>> findByDataInserimento(LocalDate dataInserimento, Pageable pageable);
    Optional<Page<Cliente>> findByDataUltimoContatto(LocalDate dataUltimoContatto, Pageable pageable);
    Optional<Cliente> findBySedeLegale(Indirizzi provinciaSedeLegale);
}
