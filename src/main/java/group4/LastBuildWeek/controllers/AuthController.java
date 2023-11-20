package group4.LastBuildWeek.controllers;


import group4.LastBuildWeek.entities.Utente;
import group4.LastBuildWeek.exceptions.BadRequestException;
import group4.LastBuildWeek.payloads.UtenteLoginDTO;
import group4.LastBuildWeek.payloads.UtenteLoginSuccessDTO;
import group4.LastBuildWeek.payloads.UtenteRegistrationDTO;
import group4.LastBuildWeek.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public UtenteLoginSuccessDTO login(@RequestBody UtenteLoginDTO body) throws Exception {

        return new UtenteLoginSuccessDTO(authService.authenticateUser(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public UtenteRegistrationDTO saveUser(@RequestBody @Validated UtenteRegistrationDTO body, BindingResult validation){
    public Utente saveUser(@RequestBody @Validated UtenteRegistrationDTO body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return authService.registerUser(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}