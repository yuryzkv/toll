package jdev.tracker.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdev.dto.PointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DataSendService {

    @Autowired
    DataStoreService dataStoreService;

    @Scheduled(cron = "${cron.take}")
    public void sendPoint() throws InterruptedException, JsonProcessingException {
       PointDTO pointDTO = dataStoreService.getPoint();
        ObjectMapper  mapper = new ObjectMapper();
        String toJson = mapper.writeValueAsString(pointDTO);
        System.out.println("Sending object pointDTO =>"+toJson);
    }
}
