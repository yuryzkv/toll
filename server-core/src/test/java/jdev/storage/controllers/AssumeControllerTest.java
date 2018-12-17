package jdev.storage.controllers;

import jdev.dto.PointDTO;
import jdev.dto.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class AssumeControllerTest {


    @Test
    public void assumePoint() throws IOException {
        AssumeController controller = new AssumeController();
        PointDTO pointDTO = new PointDTO();
        pointDTO.setTime(System.currentTimeMillis());
        Response response = controller.assumePoint(pointDTO);
        assertEquals("success",response.getMessage());
        assertTrue(response.getIsSuccess());

        pointDTO.setTime(0);
        response = controller.assumePoint(pointDTO);
        assertEquals("false",response.getMessage());
        assertFalse(response.getIsSuccess());

    }
}