package group4.LastBuildWeek.controllers;

import group4.LastBuildWeek.entities.Cliente;
import group4.LastBuildWeek.entities.Fattura;
import group4.LastBuildWeek.entities.Indirizzi;
import group4.LastBuildWeek.enums.StatoFattura;
import group4.LastBuildWeek.exceptions.BadRequestException;
import group4.LastBuildWeek.payloads.entities.NuovaFatturaDTO;
import group4.LastBuildWeek.services.FattureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/fatture")
public class FattureController {
    @Autowired
    private FattureService fattureService;


    @GetMapping("")
    public Page<Fattura> mostraFatture(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(defaultValue = "id") String orderBy){
        return fattureService.mostraFatture(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Fattura findById(@PathVariable long id){
        return fattureService.findById(id);
    }

    @GetMapping("/numeroFattura")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Fattura findByNumeroFattura(@RequestParam String numeroFattura) {
        return fattureService.findByNumeroFattura(numeroFattura);
    }

    @GetMapping("/clienteid")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Fattura> findByClienteId(@RequestParam long clienteid) {
        return fattureService.findByClienteId(clienteid);
    }

    @GetMapping("/dataFattura")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Fattura> findByDataFattura(@RequestParam LocalDate dataFattura) {
        return fattureService.findByDataFattura(dataFattura);
    }
    @GetMapping("/annoFattura")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Fattura> findByAnnoFattura(@RequestParam int anno){
        return fattureService.findByDataFatturaContaining(anno);
    }


    @GetMapping("/statoFattura")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Fattura> findByStatoFattura(@RequestParam StatoFattura statoFattura) {
        return fattureService.findByStatoFattura(statoFattura);
    }

//    @GetMapping("/rangefatture")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public List<Fattura> findByRangeImportoFattura(@RequestParam double importo1, @RequestParam double importo2){
//        return fattureService.findByRangeImportoFattura(importo1, importo2);
//    }

    @GetMapping("/rangefatture")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Fattura> findByRangeImportoFattura(@RequestParam double importo1, @RequestParam double importo2){
        return fattureService.findAllByImportoBetween(importo1, importo2);
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
    public Fattura findByIdAndUpdate(@PathVariable long id, @RequestBody Fattura body){
        return fattureService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findByIdAndDelete(@PathVariable long id){
        fattureService.findByIdAndDelete(id);
    }

}
