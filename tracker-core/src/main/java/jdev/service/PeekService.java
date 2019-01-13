package jdev.service;

import jdev.jpa.UnmarshalTrack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeekService {

    private static final Logger log = LoggerFactory.getLogger(PeekService.class);

    private ArrayList<String> pathToKmlList;

    @Autowired
    private StoreService storeService;

    @Value("${kmlCatalog.prop}")
    public String kmlCatalog;

@PostConstruct
    public void init() {
        try {
            List<File> files = Files.walk(Paths.get(kmlCatalog), 1)
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".kml"))
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            if (pathToKmlList == null) {
                pathToKmlList = new ArrayList<String>();
            } else {
                pathToKmlList.clear();
            }

            for (File file : files) {
                log.info("Add kml file name=" + file.getName());
                pathToKmlList.add(file.getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   @Scheduled(cron = "${pickupPoint.prop}")
    public void loadTrack(){
         if(pathToKmlList==null || pathToKmlList.size()==0) {init();}
        if(pathToKmlList != null && pathToKmlList.size() >0 ) {
            for (String pathToTrack : pathToKmlList) {
                UnmarshalTrack unmarshalTrack = new UnmarshalTrack();
                unmarshalTrack.setTrackPath(pathToTrack);
                unmarshalTrack.init();
                try {
                    unmarshalTrack.unmarshal();
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                storeService.saveTrack(unmarshalTrack.getTrack());
            }
            pathToKmlList.clear();
        }

    }

}
