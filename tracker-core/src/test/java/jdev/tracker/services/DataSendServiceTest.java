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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DataSendServiceTest {
    @Mock
    DataStoreService dataStoreService;
    @Mock
    RestTemplate restTemplate;
    @Mock
    Response response;
    @InjectMocks
    DataSendService mockedService;


    @Test
    public void sendPoint() throws InterruptedException, JsonProcessingException {
        when(dataStoreService.getPoint()).thenReturn(new PointDTO());
        when(response != null && response.getIsSuccess() == true).thenReturn(false);
        mockedService.sendPoint();
        assertFalse(response != null && response.getIsSuccess() == true);
    }
}