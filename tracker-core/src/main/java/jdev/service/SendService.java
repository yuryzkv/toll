package jdev.service;

import jdev.dto.PointDTO;
import jdev.dto.Response;
import jdev.jpa.Track;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SendService {

    @Autowired
    private StoreService storeService;

    private static final Logger log = LoggerFactory.getLogger(SendService.class);


    private RestTemplate restTemplate;

    private PointDTO pointA;
    private PointDTO pointB;


    public SendService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
/*
    //    @Scheduled(cron = "${cron.take}")
    public void sendPoint() throws InterruptedException, JsonProcessingException {
        if (dataStoreService == null) dataStoreService = StoreService.getInstance();
        log.info("call method getPoint() from DataSendService");
        PointDTO pointDTO = dataStoreService.getPoint();
        if (pointDTO != null) {
            if (pointA != null & pointB != null) {
                GeoData geoData = GeoLib.getGeoData(pointA, pointB);
                log.info("==> dist(m),speed(m/s),azimut1(degree),azimut2(degree): " + geoData.getDistance() + "," +
                        geoData.getSpeed() + "," + geoData.getAzimut1() + "," + geoData.getAzimut2());
                pointA = null;
                pointB = null;
            } else if (pointA == null) {
                pointA = pointDTO;
            } else if (pointB == null) {
                pointB = pointDTO;
            }
            ObjectMapper mapper = new ObjectMapper();
            String toJson = mapper.writeValueAsString(pointDTO);
            log.info("Sending object pointDTO =>" + toJson);

            HttpEntity request = new HttpEntity(pointDTO);

            Response response = restTemplate.postForObject("http://localhost:8080/place", request, Response.class);
            if (response != null && response.getIsSuccess() == true) {
                log.info("===> The object pointDTO has sent");
            } else {
                log.info("===> The object pointDTO has't sent. Something error");
            }

        }
    }*/

    @Scheduled(cron = "${sendTrack.prop}")
    public void readAllTrack() {
        List<Track> trackList = storeService.readAllTrack();
        if(trackList != null && trackList.size()>0){
            log.info("Track list size is"+trackList.size());
            for (Track track : trackList) {
                log.info("track.devId="+track.getDevId());
                sendTrack(track);
            }
        }
    }

    public void sendTrack(Track track) {
        List<PointDTO> pointDTOArrayList = storeService.readTrack(track);
        log.info("Track points size is "+pointDTOArrayList.size());
        for (PointDTO pointDTO : pointDTOArrayList) {
            pointDTO.setAutoId(track.getDevId());
            pointDTO.setRoute_name(track.getTrackName());
            HttpEntity request = new HttpEntity(pointDTO);
            Response response = restTemplate.postForObject("http://localhost:8080/place", request, Response.class);
            if (response != null && response.getIsSuccess() == true) {
                log.info("===> The object pointDTO has sent");
            } else {
                log.info("===> The object pointDTO has't sent. Something error");
            }
        }

    }
}
