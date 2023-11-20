package group4.LastBuildWeek.services;

import group4.LastBuildWeek.entities.Indirizzi;
import group4.LastBuildWeek.exceptions.NotFoundException;
import group4.LastBuildWeek.payloads.NewIndirizziDTO;
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

    public Indirizzi save(NewIndirizziDTO eventDTO) throws IOException {

        Indirizzi newEvent = new Indirizzi();
        newEvent.setVia(eventDTO.via());
        newEvent.setCivico(eventDTO.civico());
        newEvent.setLocalità(eventDTO.località());


        return indirizziRepository.save(newEvent);
    }

    public Page<Indirizzi> getAllIndirizzi(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.valueOf(orderBy));

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
        return indirizziRepository.save(found);
    }

    public void findByIdAndDelete(long id) throws NotFoundException {
        Indirizzi found = this.findById(id);
        indirizziRepository.delete(found);
    }

}
