package group4.LastBuildWeek.controllers;

import group4.LastBuildWeek.entities.Fattura;
import group4.LastBuildWeek.payloads.NuovaFatturaDTO;
import group4.LastBuildWeek.services.FattureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

public class FattureControllers {
    @Autowired
    private FattureService fattureService;


    @GetMapping("")
    public Page<Fattura> mostraFatture(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String orderBy){
        return fattureService.mostraFatture(page, size, orderBy);
    }

    @GetMapping(value = "/{id}")
    public Fattura findById(@PathVariable long id){
        return fattureService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Fattura salvaFatture(@RequestBody @Validated NuovaFatturaDTO body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return fattureService.salva(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @PutMapping("/{id}")
    public Fattura findByIdAndUpdate(@PathVariable int id, @RequestBody Fattura body){
        return fattureService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findByIdAndDelete(@PathVariable long id){
        fattureService.findByIdAndDelete(id);
    }

}
