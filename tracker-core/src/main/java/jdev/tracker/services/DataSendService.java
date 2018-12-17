package jdev.tracker.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdev.dto.GeoData;
import jdev.dto.GeoLib;
import jdev.dto.PointDTO;
import jdev.dto.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DataSendService {

    DataStoreService dataStoreService;

    private static final Logger log = LoggerFactory.getLogger(DataSendService.class);

    @Autowired
    RestTemplate restTemplate;

    private PointDTO pointA;
    private PointDTO pointB;

    @Scheduled(cron = "${cron.take}")
    public void sendPoint() throws InterruptedException, JsonProcessingException {
        if (dataStoreService == null) dataStoreService = DataStoreService.getInstance();
        log.info("call method getPoint() from DataSendService");
        PointDTO pointDTO = dataStoreService.getPoint();
        if (pointDTO != null) {
            if (pointA != null & pointB != null) {
//                GeoLib geoLib = new GeoLib();
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


            //RestTemplate restTemplate = new RestTemplate();
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
