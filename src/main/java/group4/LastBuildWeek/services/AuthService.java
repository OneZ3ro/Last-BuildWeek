package group4.LastBuildWeek.services;

import group4.LastBuildWeek.entities.Utente;
import group4.LastBuildWeek.enums.Role;
import group4.LastBuildWeek.exceptions.BadRequestException;
import group4.LastBuildWeek.exceptions.NotFoundException;
import group4.LastBuildWeek.exceptions.UnauthorizedException;
import group4.LastBuildWeek.payloads.entities.UtenteLoginDTO;
import group4.LastBuildWeek.payloads.entities.UtenteRegistrationDTO;
import group4.LastBuildWeek.repository.UtenteRepository;
import group4.LastBuildWeek.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;

@Service
public class AuthService {
    @Autowired
    private UtenteService usersService;

    @Autowired
    private UtenteRepository dipendenteRepository;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private EmailService emailService;

    public String authenticateUser(UtenteLoginDTO body) throws Exception {
        // 1. Verifichiamo che l'email dell'utente sia nel db
        Utente user = usersService.findByEmail(body.email());
        // 2. In caso affermativo, verifichiamo se la password corrisponde a quella trovata nel db
        if(bcrypt.matches(body.password(), user.getPassword()))  {
            // 3. Se le credenziali sono OK --> Genero un JWT e lo restituisco
            return jwtTools.createToken(user);
        } else {
            // 4. Se le credenziali NON sono OK --> 401
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }

    public Utente registerUser(UtenteRegistrationDTO body) throws IOException {

        // verifico se l'email è già utilizzata
        dipendenteRepository.findByEmail(body.email()).ifPresent( user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già utilizzata!");
        });
        Utente newUser = new Utente();
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setEmail(body.email());
        newUser.setUsername(body.username());
        newUser.setNome(body.nome());
        newUser.setCognome(body.cognome());
        newUser.setRole(Arrays.asList(Role.USER, Role.ADMIN));
        dipendenteRepository.save(newUser);


        String to = body.email();
        String subject = "Email di benvenuto";
        String text = "Complimenti! Registrazione su pornhub avvenuta con successo!";

        emailService.sendEmail(to, subject, text);

        return newUser;
    }

}