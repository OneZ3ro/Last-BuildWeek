package group4.LastBuildWeek.services;

import com.cloudinary.Cloudinary;
import group4.LastBuildWeek.entities.Utente;
import group4.LastBuildWeek.exceptions.NotFoundException;
import group4.LastBuildWeek.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.utils.ObjectUtils;


import java.io.IOException;
import java.util.Map;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private Cloudinary cloudinary;

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

    public Utente findByIdAndUpdate(long id, Utente body) throws NotFoundException {
        Utente found = utenteRepository.findById(id).get();
        found.setCognome(body.getCognome());
        found.setNome(body.getNome());
        found.setUsername(body.getUsername());
        found.setEmail(body.getEmail());
        //found.setPassword(bcrypt.encode(body.getPassword()));
        return utenteRepository.save(found);
    }

    public void findByIdAndDelete(long id) throws NotFoundException {
        Utente found = this.findById(id);
        utenteRepository.delete(found);
    }


    public Utente getRandomUtente() throws NotFoundException {
        return utenteRepository.findRandomUtente();
    }

    public Utente findByEmail(String email) throws Exception {
        return utenteRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("Utente con email "+ email + " non trovato"));
    }

    public String uploadAvatar(long id, MultipartFile file) throws IOException {
        try {
            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) uploadResult.get("url");

            Utente utente = utenteRepository.findById(id).orElse(null);
            if (utente != null) {
                utente.setAvatar(imageUrl);
                utenteRepository.save(utente);
            }

            return imageUrl;
        } catch (IOException e) {
            throw new RuntimeException("Impossibile caricare l'immagine", e);
        }
    }
}