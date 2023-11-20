package group4.LastBuildWeek.services;

import group4.LastBuildWeek.entities.Fattura;
import group4.LastBuildWeek.exceptions.BadRequestException;
import group4.LastBuildWeek.exceptions.NotFoundException;
import group4.LastBuildWeek.payloads.entities.NuovaFatturaDTO;
import group4.LastBuildWeek.repository.FattureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

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
                .orElseThrow(() -> new NotFoundException("Utente con email " + numeroFattura + " non trovato!"));
    }

}
