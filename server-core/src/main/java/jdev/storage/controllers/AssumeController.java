package jdev.storage.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdev.dto.PointDTO;
import jdev.dto.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class AssumeController {

    private static final Logger log = LoggerFactory.getLogger(AssumeController.class);

    @RequestMapping(value="place", method = RequestMethod.POST)
    public Response assumePoint(@RequestBody PointDTO pointDTO) throws IOException {
        log.info("Calling method assumePint() from AssumeController");
        Response response = new Response();
        ObjectMapper mapper = new ObjectMapper();
        String toJson = mapper.writeValueAsString(pointDTO);
        log.info("Assume PointDTO =>"+toJson);
        response.setMessage("success");
        response.setIsSuccess(true);
        return response;

    }

}
