package group4.LastBuildWeek.services;

import group4.LastBuildWeek.entities.Cliente;
import group4.LastBuildWeek.entities.Fattura;
import group4.LastBuildWeek.enums.StatoFattura;
import group4.LastBuildWeek.exceptions.BadRequestException;
import group4.LastBuildWeek.exceptions.NotFoundException;
import group4.LastBuildWeek.payloads.entities.NuovaFatturaDTO;
import group4.LastBuildWeek.repository.FattureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FattureService {

    @Autowired
    private FattureRepository fattureRepository;

    @Autowired
    private ClienteService clienteService;

    public Fattura salva(NuovaFatturaDTO body) throws IOException {

        // verifico se l'email è già utilizzata
        fattureRepository.findByNumeroFattura(body.numeroFattura()).ifPresent( fattura -> {
            throw new BadRequestException("Questo numero fattura " + fattura.getNumeroFattura() + " è già utilizzato!");
        });

        Fattura fatturaNuova = new Fattura();
        fatturaNuova.setDataFattura(LocalDate.now());
        fatturaNuova.setImportoFattura(body.importoFattura());
        fatturaNuova.setNumeroFattura(body.numeroFattura());
        Cliente cliente = clienteService.findById(body.clienteId());
        fatturaNuova.setCliente(cliente);
        fatturaNuova.setStatoFattura(StatoFattura.EMESSA);
        return fattureRepository.save(fatturaNuova);
    }

    public Page<Fattura> mostraFatture(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return fattureRepository.findAll(pageable);
    }

    public Fattura findById (long id) throws NotFoundException {
        return fattureRepository.findById((int) id).orElseThrow( () -> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id) throws NotFoundException{
        Fattura found = this.findById(id);
        fattureRepository.delete(found);
    }

    public Fattura findByIdAndUpdate(long id, Fattura body) throws NotFoundException{
        Fattura found = this.findById(id);
        found.setNumeroFattura(body.getNumeroFattura());
        found.setCliente(body.getCliente());
        found.setImportoFattura(body.getImportoFattura());
        found.setStatoFattura(body.getStatoFattura());
        return fattureRepository.save(found);
    }

    public Fattura findByNumeroFattura(String numeroFattura) throws NotFoundException{
        return fattureRepository.findByNumeroFattura(numeroFattura)
                .orElseThrow(() -> new NotFoundException("La fattura " + numeroFattura + " non è stata trovata!"));
    }

    public List<Fattura> findByClienteId (long clienteid) throws NotFoundException{
        Cliente cliente = clienteService.findById(clienteid);
        return fattureRepository.findByCliente(cliente).orElseThrow(()-> new NotFoundException("Cliente " + cliente + " non trovato!"));
    }

    public List<Fattura> findByStatoFattura(StatoFattura statoFattura) throws NotFoundException{
        return fattureRepository.findByStatoFattura(statoFattura).orElseThrow(() -> new NotFoundException("" + statoFattura));
    }

    public List<Fattura> findByDataFattura(LocalDate dataFattura) throws NotFoundException{
        return fattureRepository.findByDataFattura(dataFattura).orElseThrow(()-> new NotFoundException("Non ci sono fatture in data " + dataFattura + " !"));
    }

}
