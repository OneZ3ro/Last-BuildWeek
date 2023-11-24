package group4.LastBuildWeek;

import group4.LastBuildWeek.entities.Comune;
import group4.LastBuildWeek.entities.Indirizzi;
import group4.LastBuildWeek.entities.Provincia;
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
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
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
            int counter = 1;
            while ((lineProvincia = readerProvincia.readLine()) != null) {
                String[] row = lineProvincia.split(";");
                Provincia provincia = new Provincia(row[0], row[1], row[2]);
//               provinciaRepository.save(provincia);
            }
            while ((lineComune = readerComune.readLine()) != null) {
                String[] row = lineComune.split(";");
                if (row[1].equals("#RIF!")) {
                    if (counter < 10) {
                        row[1] = "00" + counter;
                    } else if (counter < 100) {
                        row[1] = "0" + counter;
                    } else {
                        row[1] = "" + counter;
                    }
                    counter++;
                }
                if (row.length == 4) {
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
