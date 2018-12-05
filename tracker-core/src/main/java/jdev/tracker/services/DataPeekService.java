package jdev.tracker.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.micromata.opengis.kml.v_2_2_0.*;

import jdev.dto.PointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataPeekService {

    private int pos;

    private ArrayList<String> pointList;

    @Autowired
    private DataStoreService dataStoreService;

    @Value("${trackPath.prop}")
    public String trackPath;


    public void readTrack() {
        try {
            List<File> files = Files.walk(Paths.get(trackPath),1)
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".kml"))
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            for (File file : files) {
                System.out.println("Trying to read kml file="+file.getName());
                final Kml kml = Kml.unmarshal(file);
                if (kml == null) {
                    System.out.println("File " + file.getName() + " don't read");
                    continue;
                }
                final Placemark placemark = (Placemark) kml.getFeature();
                Point point = (Point) placemark.getGeometry();
                List<Coordinate> coordinates = point.getCoordinates();
                System.out.println("count of coordinate="+coordinates.size());
                for (Coordinate coordinate : coordinates) {
                    System.out.println("lat=" + coordinate.getLatitude() +
                            " lon=" + coordinate.getLongitude() +
                            " alt=" + coordinate.getAltitude());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init(){
        if(pointList == null || pointList.size()==0){
            readTrack();
            pos=0;
        }else {
            pos = 0;
        }
    }

    public PointDTO getPoint(){
        if(pos > pointList.size()-1){
            init();
        }
        String s= pointList.get(pos);
        String[] strings = s.split(",");
        PointDTO pointDTO = new PointDTO();
        pointDTO.setLat(Double.valueOf(strings[0]));
        pointDTO.setLon(Double.valueOf(strings[1]));
        pointDTO.setTime(System.currentTimeMillis());
        pos++;
        return pointDTO;
    }

    @Scheduled(cron = "${cron.pickup}")
    public void pickup(){
        try {
            savePoint(getPoint());
        }catch(JsonProcessingException exp){
            exp.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void savePoint(PointDTO pointDTO) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        String toJson = mapper.writeValueAsString(pointDTO);
        if(dataStoreService == null) dataStoreService = new DataStoreService();
        dataStoreService.savePoint(toJson);
    }


}
