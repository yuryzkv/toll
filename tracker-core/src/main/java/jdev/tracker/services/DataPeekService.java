package jdev.tracker.services;

import de.micromata.opengis.kml.v_2_2_0.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataPeekService {

    @Value("${trackPath.prop}")
    public String trackPath;

    @Scheduled(cron = "${cron.prop}")
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
}
