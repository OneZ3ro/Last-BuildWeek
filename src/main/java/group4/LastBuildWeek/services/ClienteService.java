package group4.LastBuildWeek.services;

import group4.LastBuildWeek.entities.Cliente;
import group4.LastBuildWeek.exceptions.NotFoundException;
import group4.LastBuildWeek.payloads.entities.ClienteDTO;
import group4.LastBuildWeek.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Page<Cliente> getClienti(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return clienteRepository.findAll(pageable);
    }

    public Cliente findById(long id) throws NotFoundException {
        return clienteRepository.findById(id).orElseThrow(() -> new NotFoundException(id)); //---------------------------------- DA FINIRE ----------------------------------
    }

    public Cliente findByFatturatoAnnuale(double fattura) throws NotFoundException {
        return clienteRepository.findByFatturatoAnnuale(fattura).orElseThrow(() -> new NotFoundException(fattura)); //---------------------------------- DA FINIRE ----------------------------------
    }

    public List<Cliente> findByDataDiInserimento(LocalDate dataInserimento) throws NotFoundException {
        return clienteRepository.findByDataDiInserimento(dataInserimento).orElseThrow(() -> new NotFoundException(dataInserimento));  //---------------------------------- DA FINIRE ----------------------------------
    }

    public List<Cliente> findByDataDiUltimoContatto(LocalDate dataUltimoContatto) throws NotFoundException {
        return clienteRepository.findByDataDiUltimoContatto(dataUltimoContatto).orElseThrow(() -> new NotFoundException(dataUltimoContatto));
    }

    public Cliente findByProvinciaSedeLegale(String provinciaSedeLegale) throws NotFoundException {
        return clienteRepository.findByProvinciaSedeLegale(provinciaSedeLegale).orElseThrow(() -> new NotFoundException(provinciaSedeLegale));
    }

    public Cliente saveCliente(ClienteDTO body) throws IOException {
        Cliente cliente = new Cliente();
        cliente.setTipoCliente(body.tipoCliente());
        cliente.setRagioneSociale(body.ragioneSociale() + body.tipoCliente());
        cliente.setPartitaIva(body.partitaIva());
        cliente.setEmail(body.email());
        cliente.setDataInserimento(body.dataInserimento());
        cliente.setDataUltimoContatto(body.dataUltimoContatto());
        cliente.setFatturaAnnuale(body.fatturaAnnuale());
        cliente.setPec(body.pec());
        cliente.setTelefono(body.telefono());
        cliente.setEmailContatto(body.emailContatto());
        cliente.setNomeContatto(body.nomeContatto());
        cliente.setCognomeContatto(body.cognomeContatto());
        cliente.setTelefonoContatto(body.telefonoContatto());
        cliente.setLogoAziendale(body.logoAziendale());
        return clienteRepository.save(cliente);
    }

    public Cliente modificaCliente(long id, Cliente body) throws NotFoundException {
        Cliente cliente = this.findById(id);
        cliente.setTipoCliente(body.getTipoCliente());
        cliente.setRagioneSociale(body.getRagioneSociale() + body.getTipoCliente());
        cliente.setPartitaIva(body.getPartitaIva());
        cliente.setEmail(body.getEmail());
        cliente.setDataInserimento(body.getDataInserimento());
        cliente.setDataUltimoContatto(body.getDataUltimoContatto());
        cliente.setFatturaAnnuale(body.getFatturaAnnuale());
        cliente.setPec(body.getPec());
        cliente.setTelefono(body.getTelefono());
        cliente.setEmailContatto(body.getEmailContatto());
        cliente.setNomeContatto(body.getNomeContatto());
        cliente.setCognomeContatto(body.getCognomeContatto());
        cliente.setTelefonoContatto(body.getTelefonoContatto());
        cliente.setLogoAziendale(body.getLogoAziendale());
        return clienteRepository.save(cliente);
    }

    public void eliminaCliente(long id) throws NotFoundException {
        clienteRepository.deleteById(id);
    }
}
