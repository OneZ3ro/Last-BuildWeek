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

    public Fattura salva(NuovaFatturaDTO body) throws IOException {

        // verifico se l'email è già utilizzata
        fattureRepository.findByNumeroFattura(body.numeroFattura()).ifPresent( fattura -> {
            throw new BadRequestException("Questo numero fattura " + fattura.getNumeroFattura() + " è già utilizzato!");
        });

        Fattura fatturaNuova = new Fattura();
        fatturaNuova.setDataFattura(LocalDate.now());
        fatturaNuova.setImportoFattura(body.importoFattura());
        fatturaNuova.setNumeroFattura(body.numeroFattura());
        fatturaNuova.setCliente(body.cliente());
        Fattura fatturaSalvata = fattureRepository.save(fatturaNuova);
        return fatturaSalvata;
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

    public Fattura findByIdAndUpdate(int id, Fattura body) throws NotFoundException{
        Fattura found = this.findById(id);
        found.setNumeroFattura(body.getNumeroFattura());
        found.setCliente(body.getCliente());
        found.setImportoFattura(body.getImportoFattura());
        return fattureRepository.save(found);
    }

    public Fattura findByNumeroFattura(String numeroFattura){
        return fattureRepository.findByNumeroFattura(numeroFattura)
                .orElseThrow(() -> new NotFoundException("La fattura " + numeroFattura + " non è stata trovata!"));
    }

    public List<Fattura> findByCliente(Cliente cliente){
        return fattureRepository.findByCliente(cliente).orElseThrow(()-> new NotFoundException("Cliente " + cliente + " non trovato!"));
    }

    public Optional<List<Fattura>> findByStatoFattura(StatoFattura statoFattura){
        return fattureRepository.findByStatoFattura(statoFattura);
    }

    public List<Fattura> findByDataFattura(LocalDate dataFattura){
        return fattureRepository.findByDataFattura(dataFattura).orElseThrow(()-> new NotFoundException("Non ci sono fattura in data " + dataFattura + " !"));
    }

    public List<Fattura> findByYear (LocalDate dataFattura){
        SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
        String filteredYear = getYearFormat.format(dataFattura);
    }
}
