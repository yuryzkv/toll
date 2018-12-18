package jdev.tracker.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import jdev.dto.PointDTO;
import jdev.dto.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DataSendServiceInterationTest {
    @Mock
    DataStoreService dataStoreService;

    @Mock
    Response response;

    @InjectMocks
    DataSendService dataSendService = new DataSendService(new RestTemplate());


    @Test
    public void sendPoint_Integration_Test() throws InterruptedException, JsonProcessingException {
        PointDTO pointDTO = new PointDTO();
        pointDTO.setLat(56.4648268372);
        pointDTO.setLon(84.9961878732);
        pointDTO.setTime(1536393964000L);
        when(dataStoreService.getPoint()).thenReturn(pointDTO);
        when(response != null && response.getIsSuccess() == true).thenReturn(true);
        dataSendService.sendPoint();
        assertTrue(response != null && response.getIsSuccess() == true);

    }
}
