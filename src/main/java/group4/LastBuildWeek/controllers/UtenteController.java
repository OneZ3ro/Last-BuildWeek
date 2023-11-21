package group4.LastBuildWeek.controllers;

import group4.LastBuildWeek.entities.Utente;
import group4.LastBuildWeek.exceptions.NotFoundException;
import group4.LastBuildWeek.services.AuthService;
import group4.LastBuildWeek.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/utente")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Utente> getUser(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String orderBy){
        return utenteService.getUtenti(page, size, orderBy);
    }

    @GetMapping("/me")
    public UserDetails getProfile(@AuthenticationPrincipal UserDetails currentUser){
        return currentUser;
    };

    @PutMapping("/me")
    public UserDetails getProfile(@AuthenticationPrincipal Utente currentUser, @RequestBody Utente body){
        System.out.println(currentUser);
        return utenteService.findByIdAndUpdate(currentUser.getId(), body);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void getProfile(@AuthenticationPrincipal Utente currentUser){
        utenteService.findByIdAndDelete(currentUser.getId());
    };
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente findById(@PathVariable int id)  {
        return utenteService.findById(id);
    }

    @PutMapping("/{id}")
    public Utente findByIdAndUpdate(@PathVariable int id, @RequestBody Utente body) throws NotFoundException {
        return utenteService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findByIdAndDelete(@PathVariable int id) throws NotFoundException {
        utenteService.findByIdAndDelete(id);
    }

    @PostMapping("/upload/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String uploadAvatar(@PathVariable long id, @RequestParam("immagine_profilo") MultipartFile body) throws IOException {
        System.out.println(body.getSize());
        System.out.println(body.getContentType());
        return utenteService.uploadAvatar(id,body);
    }

}
