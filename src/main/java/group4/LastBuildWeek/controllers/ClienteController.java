package group4.LastBuildWeek.controllers;

import group4.LastBuildWeek.entities.Cliente;
import group4.LastBuildWeek.entities.Indirizzi;
import group4.LastBuildWeek.exceptions.BadRequestException;
import group4.LastBuildWeek.payloads.entities.ClienteDTO;
import group4.LastBuildWeek.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/clienti")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("")
    public Page<Cliente> getClienti(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size,
                                    @RequestParam(defaultValue = "clienteId") String orderBy) {
        return clienteService.getClienti(page, size, orderBy);
    }

    @GetMapping("/fatturatoAnnuale")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Cliente findByFatturatoAnnuale(@RequestParam double fattura) {
        return clienteService.findByFatturatoAnnuale(fattura);
    }

    @GetMapping("/dataInserimento")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Cliente> findByDataDiInserimento(@RequestParam LocalDate dataInserimento) {
        return clienteService.findByDataDiInserimento(dataInserimento);
    }

    @GetMapping("/dataUltimoContatto")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Cliente> findByDataDiUltimoContatto(@RequestParam LocalDate dataUltimoContatto) {
        return clienteService.findByDataDiUltimoContatto(dataUltimoContatto);
    }

    @GetMapping("/provinciaSedeLegale")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Cliente findByProvinciaSedeLegale(@RequestParam Indirizzi provinciaSedeLegale) {
        return clienteService.findByProvinciaSedeLegale(provinciaSedeLegale);
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Cliente> findByNomeContattoContaining(@RequestParam String nomeContatto) {
        return clienteService.findByNomeContattoContaining(nomeContatto);
    }


    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente creaCliente(@RequestBody @Validated ClienteDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return clienteService.saveCliente(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping("/{id}")
    public Cliente findById(@PathVariable long id) {
        return clienteService.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Cliente modificaCliente(@PathVariable long id, @RequestBody Cliente body) {
        return clienteService.modificaCliente(id, body);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void eliminaCliente(@PathVariable long id) {
        clienteService.eliminaCliente(id);
    }

    @PostMapping("/{id}/upload")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String uploadFiles(@PathVariable long id, @RequestParam("img")MultipartFile body) throws IOException {
        return clienteService.uploadFiles(id, body);
    }
}
