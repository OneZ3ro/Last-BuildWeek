package group4.LastBuildWeek;

import group4.LastBuildWeek.entities.Comune;
import group4.LastBuildWeek.entities.Provincia;
import group4.LastBuildWeek.repository.ComuneRepository;
import group4.LastBuildWeek.repository.ProvinciaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;

@Component
@Slf4j
public class MyRunner implements CommandLineRunner {
    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private ComuneRepository comuneRepository;

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
//                provinciaRepository.save(provincia);
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

//                    comuneRepository.save(comune);
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            readerProvincia.close();
        }
    }
}
