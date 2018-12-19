package jdev.tracker.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import jdev.dto.PointDTO;
import jdev.dto.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DataSendServiceTest {
    @Mock
    DataStoreService dataStoreService;
    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    DataSendService dataSendService = new DataSendService(restTemplate);


    @Test
    public void sendPoint() throws InterruptedException, JsonProcessingException {
        PointDTO pointDTO = new PointDTO();
        pointDTO.setLat(56.463951515);
        pointDTO.setLon(84.9944938906);
        pointDTO.setTime(1536393830000L);
        when(dataStoreService.getPoint()).thenReturn(pointDTO);
        HttpEntity request = new HttpEntity(pointDTO);
        when(restTemplate.postForObject("http://localhost:8080/place", request, Response.class)).thenReturn(new Response());
        dataSendService.sendPoint();
        verify(restTemplate).postForObject("http://localhost:8080/place", request, Response.class);
    }


}