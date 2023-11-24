package group4.LastBuildWeek;

import group4.LastBuildWeek.entities.Comune;
import group4.LastBuildWeek.entities.Indirizzi;
import group4.LastBuildWeek.entities.Provincia;

import group4.LastBuildWeek.exceptions.NotFoundException;

import group4.LastBuildWeek.enums.StatoFattura;
import group4.LastBuildWeek.enums.TipoCliente;
import group4.LastBuildWeek.payloads.entities.ClienteDTO;
import group4.LastBuildWeek.payloads.entities.NewIndirizziDTO;
import group4.LastBuildWeek.payloads.entities.NuovaFatturaDTO;

import group4.LastBuildWeek.repository.ComuneRepository;
import group4.LastBuildWeek.repository.ProvinciaRepository;
import group4.LastBuildWeek.services.ClienteService;
import group4.LastBuildWeek.services.FattureService;
import group4.LastBuildWeek.services.IndirizziService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.*;

import java.time.LocalDate;


@Component
@Slf4j
public class MyRunner implements CommandLineRunner {
    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private ComuneRepository comuneRepository;

    @Autowired
    private IndirizziService indirizziService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private FattureService fattureService;

    @Override
    public void run(String... args) throws Exception {
        String fileProvincia = "src/main/java/group4/LastBuildWeek/mycsvfiles\\province-italiane.csv";
        String fileComune = "src/main/java/group4/LastBuildWeek/mycsvfiles\\comuni-italiani.csv";
        BufferedReader readerProvincia = null;
        BufferedReader readerComune = null;
        String lineProvincia = "";
        String lineComune = "";
        try {
            readerProvincia = new BufferedReader(new FileReader(fileProvincia));
            readerComune = new BufferedReader(new FileReader(fileComune));
            int counterComune = 1;
            int counterProvincia = 1;
            while ((lineProvincia = readerProvincia.readLine()) != null) {
                String[] row = lineProvincia.split(";");
//                if (!row[0].equals("Sigla")) {
//                    Provincia provincia = new Provincia(row[0], row[1], row[2]);
//                    provinciaRepository.save(provincia);
//                }
                Provincia provincia = new Provincia(row[0], row[1], row[2]);

 //               provinciaRepository.save(provincia);

//               provinciaRepository.save(provincia);

            }
            List<String> app = new ArrayList<>();
            while ((lineComune = readerComune.readLine()) != null) {
                String[] row = lineComune.split(";");
                if (row[1].equals("#RIF!")) {
                    if (counterComune < 10) {
                        row[1] = "00" + counterComune;
                    } else if (counterComune < 100) {
                        row[1] = "0" + counterComune;
                    } else {
                        row[1] = "" + counterComune;
                    }
                    counterComune++;
                }
                if (row.length == 4) {
                    Provincia p = new Provincia();
                    switch (row[3]) {
                        case "La Spezia": {
                            p = provinciaRepository.findByProvincia("La-Spezia").get();
                            p.setProvincia("La-Spezia");
                            provinciaRepository.save(p);
                            break;
                        }
                        case "Valle d'Aosta/Vallée d'Aoste":  {
                            p = provinciaRepository.findByProvincia("Aosta").get();
                            p.setProvincia("Aosta");
                            provinciaRepository.save(p);
                            break;
                        }
                        case "Vibo Valentia":  {
                            p = provinciaRepository.findByProvincia("Vibo-Valentia").get();
                            p.setProvincia("Vibo-Valentia");
                            provinciaRepository.save(p);
                            break;
                        }
                        case "Reggio nell'Emilia":  {
                            p = provinciaRepository.findByProvincia("Reggio-Emilia").get();
                            p.setProvincia("Reggio-Emilia");
                            provinciaRepository.save(p);
                            break;
                        }
                        case "Reggio Calabria":  {
                            p = provinciaRepository.findByProvincia("Reggio-Calabria").get();
                            p.setProvincia("Reggio-Calabria");
                            provinciaRepository.save(p);
                            break;
                        }
                        case "Monza e della Brianza":  {
                            p = provinciaRepository.findByProvincia("Monza-Brianza").get();
                            p.setProvincia("Monza-Brianza");
                            provinciaRepository.save(p);
                            break;
                        }
                        case "Pesaro e Urbino":  {
                            p = provinciaRepository.findByProvincia("Pesaro-Urbino").get();
                            p.setProvincia("Pesaro-Urbino");
                            provinciaRepository.save(p);
                            break;
                        }
                        case "Sud Sardegna":  {
                            p = provinciaRepository.findByProvincia("Cagliari").get();
                            p.setProvincia("Cagliari");
                            provinciaRepository.save(p);
                            break;
                        }
                        case "Ascoli Piceno":  {
                            p = provinciaRepository.findByProvincia("Ascoli-Piceno").get();
                            p.setProvincia("Ascoli-Piceno");
                            provinciaRepository.save(p);
                            break;
                        }
                        case "Verbano-Cusio-Ossola":  {
                            p = provinciaRepository.findByProvincia("Verbania").get();
                            p.setProvincia("Verbania");
                            provinciaRepository.save(p);
                            break;
                        }
                        case "Bolzano/Bozen":  {
                            p = provinciaRepository.findByProvincia("Bolzano").get();
                            p.setProvincia("Bolzano");
                            provinciaRepository.save(p);
                            break;
                        }
                        case "Forlì-Cesena":  {
                            p = provinciaRepository.findByProvincia("Forli-Cesena").get();
                            p.setProvincia("Forli-Cesena");
                            provinciaRepository.save(p);
                            break;
                        }
                        default: {
                            p = provinciaRepository.findByProvincia(row[3]).get();
                            break;
                        }
                    }
                    Comune comune = new Comune(row[0], row[1], row[2], p.getProvincia(), p);
//                    comuneRepository.save(comune);
                }
            }
            Set<String> app2 = new HashSet<>(app);
            app2.forEach(System.out::println);
                    Provincia p = provinciaRepository.findByProvincia(row[3]).orElse(null);
                    Comune comune = new Comune(row[0], row[1], row[2], row[3], p);
//                   comuneRepository.save(comune);
                }
                //indirizzi
                //clienti con indirizzo
                //fattura con cliente

            }
            //scommentare una linea alla volta
            for(int i = 1;i<100;i++){
            //    NewIndirizziDTO indirizzi = new NewIndirizziDTO("Via "+i,"Civico "+i,"cap"+ i, "Torino",i);
              //  NewIndirizziDTO indirizzi = new NewIndirizziDTO("Via "+i,"Civico "+i,"cap"+ i, "Cuneo",i+500);
                //NewIndirizziDTO indirizzi = new NewIndirizziDTO("Via "+i,"Civico "+i,"cap"+ i, "Alessandria",i+900);
                //NewIndirizziDTO indirizzi = new NewIndirizziDTO("Via "+i,"Civico "+i,"cap"+ i, "Bergamo",i+1800);
                //NewIndirizziDTO indirizzi = new NewIndirizziDTO("Via "+i,"Civico "+i,"cap"+ i, "Brescia",i+2000);
              //  indirizziService.save(indirizzi);
            }
            //inserire dopo indirizzi, cambiare indice(+50) e tipo cliente
            for(int i =1 ;i<50;i++){
                ClienteDTO clienteDTO = new ClienteDTO(TipoCliente.SRL,"ragioneSociale"+TipoCliente.PA+i,"partitaIva"+TipoCliente.PA+i,"email"+i+"@gmail.com",
                        LocalDate.now().minusDays(i+i), LocalDate.now().minusDays(i),100+i,"pec"+i+"@gmail.com","888777333"+i,"email"+i+"@gmail.com",
                        "nome"+i,"cognome"+i,"888777333"+i,"",i+85552,i+85552);

               // clienteService.saveCliente(clienteDTO);
            }
            //inserire doipo indirizzi e cliente
            for(int i =1 ;i<50;i++){
                NuovaFatturaDTO nuovaFatturaDTO = new NuovaFatturaDTO(i+""+i+""+i,10+i,i, StatoFattura.EMESSA);
               fattureService.salva(nuovaFatturaDTO);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            readerProvincia.close();
        }
    }
}
