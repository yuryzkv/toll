package jdev.storage.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdev.dto.PointDTO;
import jdev.dto.Response;
import jdev.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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

    @RequestMapping(value = "/allroutes", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Object> allRoutes(){
        List<PointDTO> routes = storeService.getRoutes();
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(routes, HttpStatus.OK);
        return responseEntity;
    }

    /*@RequestMapping(value = "/last/points/{routeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getLastPointsByNumber(@PathVariable("routeId") int routeId, Model model){
        List<PointDTO> placeMarks = storeService.getLastPoints(routeId);
        log.info("placeMarks array size is "+placeMarks.size());
        model.addAttribute("placeMarks",placeMarks);
        return "jsonTemplate";
    }*/

    /*@RequestMapping(value = "/last/points/{routeId}", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<Object> getLastPointsByNumber(@PathVariable("routeId") int routeId){
        List<PointDTO> placeMarks = storeService.getLastPoints(routeId);
        log.info("placeMarks array size is "+placeMarks.size());
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(placeMarks,HttpStatus.OK);
        return responseEntity;
    }*/

    @RequestMapping(value = "/last/points/{routeId}", method = RequestMethod.GET)
    public @ResponseBody List<PointDTO> getLastPointsByNumber(@PathVariable("routeId") int routeId) {
        List<PointDTO> placeMarks = storeService.getLastPoints(routeId);
        log.info("placeMarks array size is " + placeMarks.size());
//        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(placeMarks,HttpStatus.OK);
        return placeMarks;
    }

    @RequestMapping(value = "/routes",  produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public String getAllRoutesJSON(Model model){
        List<PointDTO> routes = storeService.getRoutes();
        log.info("placeMarks array size is "+routes.size());
        model.addAttribute("routes",routes);
        return "jsonTemplate";
    }
}
