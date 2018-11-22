package jdev.tracker.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdev.dto.PointDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

@Service
public class DataStoreService {

    private BlockingDeque<PointDTO> queue = new LinkedBlockingDeque<PointDTO>(100);

    public void savePoint(String json) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        PointDTO pointDTO = mapper.readValue(json,PointDTO.class);
        queue.put(pointDTO);
    }

    public PointDTO getPoint() throws InterruptedException {
        PointDTO pointDTO = queue.take();
        return pointDTO;
    }
}
