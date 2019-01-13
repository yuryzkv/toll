package jdev.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UnmarshalTrack {

    private static final Logger log = LoggerFactory.getLogger(UnmarshalTrack.class);
    // The path to track kml file
    private String trackPath;
    // The POJO
    private LandMark landMark;
    // The unmarshal track
    private Track track;

    public Track getTrack() {
        return track;
    }

    // Set path to track kml file
    public String getTrackPath() {
        return trackPath;
    }

    // Get path to track kml file
    public void setTrackPath(String trackPath) {
        this.trackPath = trackPath;
    }


    public UnmarshalTrack() {
        init();
    }

    public void init(){
        if (track == null) track = new Track();
        if (track.getTrackPoints() == null) {
            track.setTrackPoints(new ArrayList<LandMark>());
        } else {
            track.getTrackPoints().clear();
        }
    }



    public void unmarshal() throws XMLStreamException, ParseException {
        XMLInputFactory xif = XMLInputFactory.newFactory();
        log.info("trackPath = " + trackPath);
        StreamSource xml = new StreamSource(trackPath);
        XMLStreamReader xsr = xif.createXMLStreamReader(xml);

        while (xsr.hasNext()) {

            xsr.next();

            if (xsr.isStartElement()) {
                log.info("xsr.element=" + xsr.getLocalName());
                if (xsr.getAttributeCount() > 0) {
                    String name = xsr.getAttributeLocalName(0);
                    log.info("attr's name =" + name);
                    String value = xsr.getAttributeValue(0);
                    log.info("attr's value=" + value);
                    if (value.equals("time")) {
                        xsr.next();
                        if (xsr.isCharacters()) {
                            value = xsr.getText();
                            log.info("time's value=" + value);
                            if (landMark == null) landMark = new LandMark();
                            landMark.setTime(value);
                        }
                    }
                } else if (xsr.getLocalName().equals("LineString")) {
                    xsr.next();
                } else if (xsr.getLocalName().equals("coordinates")) {
                    xsr.next();
                    String text = xsr.getText();
                    log.info("coordinates's text=" + text);
                    if (landMark == null) landMark = new LandMark();
                    landMark.setCoordinates(text);

                } else if (xsr.getLocalName().equals("Placemark")) {
                    landMark = new LandMark();
                }
            } else if (xsr.isEndElement()) {
                if (xsr.getLocalName().equals("Placemark")) {
                    if (track.trackPoints == null) track.trackPoints = new ArrayList<LandMark>();
                    if (landMark != null) {
                        track.trackPoints.add(landMark);
                        landMark = null;
                    }
                }
            }
        }
        if (track.trackPoints.size() > 0) {
            for (LandMark landMark : track.trackPoints) {
                log.info(landMark.toString());
            }
        }
        String name = new File(trackPath).getName();
        Pattern r = Pattern.compile(".*\\_(.+)(\\.[kK][mM][lL])");
        Matcher m = r.matcher(name);
        while (m.find()) {
            track.devId = m.group(1);
        }
        int dotIndex = name.lastIndexOf('.');
        track.trackName = (dotIndex == -1) ? name : name.substring(0, dotIndex);


//Unmarshalling the middle of xml document using jaxb

    }

}