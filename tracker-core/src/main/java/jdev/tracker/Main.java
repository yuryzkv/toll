package jdev.tracker;

import jdev.dto.PointDTO;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String... args) throws Exception {
/*
for (int i=0; i<5; i++) {
System.out.println("Main.main say Hello!!!!");
PointDTO point = new PointDTO();
point.setLat(45);
System.out.println(point.toJson());
Thread.sleep(1000);
}
*/

        List<File> files  = Files.walk(Paths.get("C:\\bin\\proj\\toll-services\\track-data\\"))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());


        for(File file : files){
            System.out.println(file.getName());
        }

    }
}
