package jdev.tracker.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdev.dto.PointDTO;
import jdev.tracker.UnmarshalTrackContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

@Service
public class UnmarshalTrackService {
    
    private static final Logger log = LoggerFactory.getLogger(UnmarshalTrackContext.class);

    @Value("${unmarshalTrack.prop}")
    public String pathTrack;

    public LandMark landMark;
    public ArrayList<LandMark> landMarks;

    private DataStoreService dataStoreService;

    private int pos;


    @PostConstruct
    public void init() throws XMLStreamException, ParseException {
        if (landMarks == null || landMarks.size() == 0) {
            unmarshal();
            pos = 0;
        } else {
            pos = 0;
        }
    }

    @Scheduled(cron = "${cron.pickup}")
    public void pickup() {
        log.info("===> call method pickup()");
        try {
            saveDTO(getPoint());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JsonProcessingException exp) {
            exp.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PointDTO getPoint() throws XMLStreamException, ParseException {
        if (pos > landMarks.size() - 1) {
            init();
        }
        LandMark landMark = landMarks.get(pos);
        PointDTO pointDTO = new PointDTO();
        pointDTO.setLat(landMark.getLat());
        pointDTO.setLon(landMark.getLon());
        pointDTO.setTime(landMark.getCurren_time());
        pos++;
        return pointDTO;
    }



    public void saveDTO(PointDTO pointDTO) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        String toJson = mapper.writeValueAsString(pointDTO);
        if (dataStoreService == null) dataStoreService = DataStoreService.getInstance();
        dataStoreService.savePoint(toJson);
    }


    public void unmarshal() throws XMLStreamException, ParseException {
        XMLInputFactory xif = XMLInputFactory.newFactory();
        log.info("pathTrack = " + pathTrack);
        StreamSource xml = new StreamSource(pathTrack);
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
                    if (landMarks == null) landMarks = new ArrayList<LandMark>();
                    if (landMark != null) {
                        landMarks.add(landMark);
                        landMark = null;
                    }
                }
            }
        }
        if (landMarks.size() > 0) {
            for (LandMark landMark : landMarks) {
                log.info(landMark.toString());
            }
        }
//Unmarshalling the middle of xml document using jaxb

    }

}
