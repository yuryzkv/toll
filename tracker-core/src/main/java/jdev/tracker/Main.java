package jdev.tracker;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String... args) throws Exception {

       /* List<File> files  = Files.walk(Paths.get("C:\\bin\\proj\\toll-services\\track-data\\"))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());

        for(File file : files){
            System.out.println(file.getName());
        }*/
        String path = "C:\\bin\\proj\\toll-services\\track-data\\Track_ITKARA_08-09-2018.kml";
        List<String> files = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        System.out.println("count of strings=" + files.size());
        Pattern pat = Pattern.compile("(<coordinates>)(.*?)(</coordinates>)");
        List<String> sb = null;
        for (String str : files) {
            Matcher m = pat.matcher(str);
            while (m.find()) {
                if (sb == null) sb = new ArrayList<String>();
                System.out.println("string is found =>" + m.group(2));
                sb.add(m.group(2));
            }
        }

        String s1 = "C:\\bin\\proj\\toll-services\\track-data\\test.kml";
        List<String> lines = Files.readAllLines(Paths.get(s1), StandardCharsets.UTF_8);

        List<String> sb1 = null;
        for (String line : lines) {
            if (sb1 == null) sb1 = new ArrayList<String>();

            if (line.indexOf("<coordinates>") != -1) {
                sb1.add("<coordinates>");
                sb1.addAll(sb);
                sb1.add("</coordinates>");
                continue;
            }
            sb1.add(line);
        }

        if (sb1 != null && sb1.size() > 0) {
            String s2 = "C:\\bin\\proj\\toll-services\\track-data\\test1.kml";
            Path p2 = Paths.get(s2);
            Files.write(p2, sb1, StandardCharsets.UTF_8);
        }
    }
}
