package group4.LastBuildWeek.repositories;

import group4.LastBuildWeek.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByNome(String nome);
    Optional<Cliente> findByFatturatoAnnuale(double fatturato);
    Optional<List<Cliente>> findByDataDiInserimento(LocalDate dataInserimento);
    Optional<List<Cliente>> findByDataDiUltimoContatto(LocalDate dataUltimoContatto);
    Optional<Cliente> findByProvinciaSedeLegale(String provinciaSedeLegale);
}
