package group4.LastBuildWeek.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import group4.LastBuildWeek.entities.Cliente;
import group4.LastBuildWeek.entities.Indirizzi;
import group4.LastBuildWeek.exceptions.NotFoundException;
import group4.LastBuildWeek.payloads.entities.ClienteDTO;
import group4.LastBuildWeek.repository.ClienteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private IndirizziService indirizziService;

    public Page<Cliente> getClienti(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return clienteRepository.findAll(pageable);
    }

    public Cliente findById(long id) throws NotFoundException {
        return clienteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Cliente findByFatturatoAnnuale(double fattura) throws NotFoundException {
        return clienteRepository.findByFatturaAnnuale(fattura).orElseThrow(() -> new NotFoundException(fattura));
    }

    public List<Cliente> findByDataDiInserimento(LocalDate dataInserimento) throws NotFoundException {
        return clienteRepository.findByDataInserimento(dataInserimento).orElseThrow(() -> new NotFoundException(dataInserimento));
    }

    public List<Cliente> findByDataDiUltimoContatto(LocalDate dataUltimoContatto) throws NotFoundException {
        return clienteRepository.findByDataUltimoContatto(dataUltimoContatto).orElseThrow(() -> new NotFoundException(dataUltimoContatto));
    }

    public Cliente findByProvinciaSedeLegale(Indirizzi provinciaSedeLegale) throws NotFoundException {
        return clienteRepository.findBySedeLegale(provinciaSedeLegale).orElseThrow(() -> new NotFoundException(String.valueOf(provinciaSedeLegale)));
    }

    public List<Cliente> findByNomeContattoContaining(String nomeContatto) throws NotFoundException {
//        @Query("SELECT c FROM Cliente c WHERE c.nomeContatto LIKE '?1%'")
//        List<Cliente> findByNomeContaining(String partialNome);
//        TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.nomeContatto LIKE ':nome%'", Cliente.class);
//        query.setParameter("mezzoId", idConvertito);
        return clienteRepository.findByNomeContattoContaining(nomeContatto).orElseThrow(() -> new NotFoundException(nomeContatto));
    }


    public Cliente saveCliente(ClienteDTO body) throws IOException {
        Cliente cliente = new Cliente();
        System.out.println(body.sedeLegaleId());
        System.out.println(body.sedeOperativaId());
        cliente.setTipoCliente(body.tipoCliente());
        cliente.setRagioneSociale(body.ragioneSociale() + body.tipoCliente());
        cliente.setPartitaIva(body.partitaIva());
        cliente.setEmail(body.email());
        cliente.setDataInserimento(LocalDate.now());
        cliente.setDataUltimoContatto(body.dataUltimoContatto());
        cliente.setFatturaAnnuale(body.fatturaAnnuale());
        cliente.setPec(body.pec());
        cliente.setTelefono(body.telefono());
        cliente.setEmailContatto(body.emailContatto());
        cliente.setNomeContatto(body.nomeContatto());
        cliente.setCognomeContatto(body.cognomeContatto());
        cliente.setTelefonoContatto(body.telefonoContatto());
        cliente.setLogoAziendale(body.logoAziendale());
        if (body.sedeLegaleId() != 0) {
            Indirizzi indirizzoLegale = indirizziService.findById(body.sedeLegaleId());
            cliente.setSedeLegale(indirizzoLegale);
            Indirizzi indirizzoOperativo = indirizziService.findById(body.sedeOperativaId());
            cliente.setSedeOperativa(indirizzoOperativo);
        }
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
        cliente.setSedeLegale(body.getSedeLegale());
        cliente.setSedeOperativa(body.getSedeOperativa());
        return clienteRepository.save(cliente);
    }

    public void eliminaCliente(long id) throws NotFoundException {
        clienteRepository.deleteById(id);
    }

    public String uploadFiles(long id, MultipartFile file) throws IOException {
        String urlImg = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        Cliente cliente = this.findById(id);
        cliente.setLogoAziendale(urlImg);
        clienteRepository.save(cliente);
        return urlImg;
    }
}
