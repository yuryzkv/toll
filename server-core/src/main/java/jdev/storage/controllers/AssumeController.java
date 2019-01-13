package jdev.storage.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdev.dto.PointDTO;
import jdev.dto.Response;
import jdev.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;

@RestController
public class AssumeController {

    @Autowired
    public StoreService storeService;

    private static final Logger log = LoggerFactory.getLogger(AssumeController.class);

    /*
     @RequestMapping(value="place", method = RequestMethod.POST)
     public Response assumePoint(@RequestBody PointDTO pointDTO) throws IOException {
         log.info("Calling method assumePint() from AssumeController");
         Response response = new Response();
         ObjectMapper mapper = new ObjectMapper();
         String toJson = mapper.writeValueAsString(pointDTO);
         log.info("Assume PointDTO =>"+toJson);
         if(pointDTO.getTime() !=0){
             response.setMessage("success");
             response.setIsSuccess(true);
         }else{
             response.setMessage("false");
             response.setIsSuccess(false);
         }
         return response;

     }
 */
    @RequestMapping(value = "place", method = RequestMethod.POST)
    public Response assumePoint(@RequestBody PointDTO pointDTO) throws IOException {
        log.info("Calling method assumePint() from AssumeController");

        ObjectMapper mapper = new ObjectMapper();
        String toJson = mapper.writeValueAsString(pointDTO);
        log.info("Assume PointDTO =>" + toJson);
        Response response = new Response();

        if(pointDTO.getTime() == 0 || pointDTO.getAutoId() == null ||
                pointDTO.getRoute_name() ==null || pointDTO.getLat() ==0 ||
                pointDTO.getLon() ==0){
            response.setMessage("false");
            response.setIsSuccess(false);
        }else{
            storeService.savePoint(pointDTO);
            response.setMessage("success");
            response.setIsSuccess(true);
        }
        return response;
    }
}
