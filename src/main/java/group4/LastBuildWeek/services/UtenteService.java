package group4.LastBuildWeek.services;

import group4.LastBuildWeek.entities.Utente;
import group4.LastBuildWeek.exceptions.NotFoundException;
import group4.LastBuildWeek.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;


    // @Autowired
    //private EmailSender emailSender;

    /*public Dipendente save(DipendenteDTO body) throws IOException {
        System.out.println(body.nome());
        dipendenteRepository.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestExceptions("L'email " + body.email() + " è già in uso!");
        });
        Dipendente dipendente = new Dipendente(body.username(), body.nome(), body.cognome(), body.email(), body.password());
        return dipendenteRepository.save(dipendente);
    }*/

    public Page<Utente> getUtenti(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));

        return utenteRepository.findAll(pageable);
    }

    public Utente findById(long id) throws NotFoundException {
        return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id) throws NotFoundException {
        Utente found = this.findById(id);
        utenteRepository.delete(found);
    }

    public Utente findByIdAndUpdate(long id, Utente body) throws NotFoundException {
        Utente found = this.findById(id);

        return utenteRepository.save(found);
    }

    public Utente getRandomUtente() throws NotFoundException {
        return utenteRepository.findRandomUtente();
    }

    public Utente findByEmail(String email) throws Exception {
        return utenteRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("Utente con email "+ email + " non trovato"));
    }

}