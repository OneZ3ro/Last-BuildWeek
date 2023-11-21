package group4.LastBuildWeek.services;

import group4.LastBuildWeek.entities.Comune;
import group4.LastBuildWeek.entities.Indirizzi;
import group4.LastBuildWeek.exceptions.NotFoundException;
import group4.LastBuildWeek.payloads.entities.NewIndirizziDTO;
import group4.LastBuildWeek.repository.ComuneRepository;
import group4.LastBuildWeek.repository.IndirizziRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class IndirizziService {
    @Autowired
    private IndirizziRepository indirizziRepository;
    @Autowired
    private ComuneRepository comuneRepository;

    public Indirizzi save(NewIndirizziDTO indirizzoDTO) throws IOException {

        Indirizzi newIndirizzo = new Indirizzi();
        newIndirizzo.setVia(indirizzoDTO.via());
        newIndirizzo.setCivico(indirizzoDTO.civico());
        newIndirizzo.setLocalità(indirizzoDTO.località());
        newIndirizzo.setCap(indirizzoDTO.cap());
        newIndirizzo.setComune(indirizzoDTO.comune());
        Comune c = comuneRepository.findById(indirizzoDTO.comuneId()).orElseThrow(() -> new NotFoundException(indirizzoDTO.comuneId()));
        newIndirizzo.setComuneId(c);


        return indirizziRepository.save(newIndirizzo);
    }

    public Page<Indirizzi> getAllIndirizzi(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return indirizziRepository.findAll(pageable);
    }

    public Indirizzi findById(long id) throws NotFoundException {
        return indirizziRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    public Indirizzi findByIdAndUpdate(long id, NewIndirizziDTO body) throws NotFoundException {
        Indirizzi found = this.findById(id);
        found.setVia(body.via());
        found.setCivico(body.civico());
        found.setLocalità(body.località());
        found.setCap(body.cap());
        found.setComune(body.comune());
        Comune c = comuneRepository.findById(body.comuneId()).orElseThrow(() -> new NotFoundException(body.comuneId()));
        found.setComuneId(c);
        return indirizziRepository.save(found);
    }

    public void findByIdAndDelete(long id) throws NotFoundException {
        Indirizzi found = this.findById(id);
        indirizziRepository.delete(found);
    }

}
