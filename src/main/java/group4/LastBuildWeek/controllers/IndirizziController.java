package group4.LastBuildWeek.controllers;

import group4.LastBuildWeek.entities.Indirizzi;
import group4.LastBuildWeek.exceptions.BadRequestException;
import group4.LastBuildWeek.payloads.NewIndirizziDTO;
import group4.LastBuildWeek.services.IndirizziService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/indirizzi")
public class IndirizziController {
    @Autowired
    private IndirizziService indirizziService;

    @GetMapping("")
    public Page<Indirizzi> getUser(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String orderBy) {
        return indirizziService.getAllIndirizzi(page, size, orderBy);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Indirizzi saveIndirizzo(@RequestBody @Validated NewIndirizziDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return indirizziService.save(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @PutMapping("/{id}")
    public Indirizzi updateIndirizzo(@PathVariable long id, @RequestBody NewIndirizziDTO body) {
        return indirizziService.findByIdAndUpdate(id, body);
    }

    @GetMapping("/{id}")
    public Indirizzi findById(@PathVariable long id) {
        return indirizziService.findById(id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void getProfile(@PathVariable long id) {
        indirizziService.findByIdAndDelete(id);
    }

    ;

}
