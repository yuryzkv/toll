package jdev.tracker.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdev.dto.GeoData;
import jdev.dto.GeoLib;
import jdev.dto.PointDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DataSendService {

    DataStoreService dataStoreService;

    private PointDTO pointA;
    private PointDTO pointB;

    @Scheduled(cron = "${cron.take}")
    public void sendPoint() throws InterruptedException, JsonProcessingException {
        if(dataStoreService == null) dataStoreService = DataStoreService.getInstance();
        System.out.println("call method getPoint() from DataSendService");
        PointDTO pointDTO = dataStoreService.getPoint();
        if(pointDTO != null) {
            if(pointA != null & pointB != null){
                GeoLib geoLib = new GeoLib();
                GeoData geoData = geoLib.getGeoData(pointA,pointB);
                System.out.println("==> dist(m),speed(m/s),azimut1(degree),azimut2(degree): "+geoData.getDistance()+","+
                                geoData.getSpeed()+","+ geoData.getAzimut1()+","+geoData.getAzimut2());
                pointA = null;
                pointB = null;
            }else if(pointA == null){
                pointA = pointDTO;
            }else if(pointB == null){
                pointB = pointDTO;
            }
            ObjectMapper mapper = new ObjectMapper();
            String toJson = mapper.writeValueAsString(pointDTO);
            System.out.println("Sending object pointDTO =>" + toJson);
        }
    }
}
