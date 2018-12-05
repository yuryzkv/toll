package jdev.tracker.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdev.dto.PointDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

@Service
public class DataStoreService {

    private static DataStoreService dataStoreService;

    private BlockingDeque<PointDTO> queue = new LinkedBlockingDeque<PointDTO>(100);


    public static synchronized DataStoreService getInstance(){
        if(dataStoreService == null){
            dataStoreService = new DataStoreService();
        }
        return dataStoreService;
    }

    public void savePoint(String json) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        PointDTO pointDTO = mapper.readValue(json,PointDTO.class);
        System.out.println("===> sending DTO to queue");
        queue.put(pointDTO);
    }

    public PointDTO getPoint() throws InterruptedException {
//      PointDTO pointDTO = queue.take();
        System.out.println("===> taking DTO from queue");
        PointDTO pointDTO = queue.poll(500,TimeUnit.MILLISECONDS);
        return pointDTO;
    }
}
