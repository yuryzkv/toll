package jdev.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdev.dto.PointDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebMainTest {

    private RestTemplate restTemplate;


    public WebMainTest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static void main(String... args) {
        WebMainTest webMainTest = new WebMainTest(new RestTemplate());
        webMainTest.getRoutes();
        webMainTest.getLastPoints();
        webMainTest.getAllRoutes();
    }

    private void getRoutes() {
        final String uri = "http://localhost:8080/allroutes";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<PointDTO>> result = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<PointDTO>>() {
        });
        System.out.println(result);
        List<PointDTO> listPointDTO = result.getBody();
        listPointDTO.forEach(n -> print(n));
    }

    private void getAllRoutes() {
        final String uri = "http://localhost:8080/routes";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        System.out.println(result);
    }

   /* private void getLastPoints() {
        final String uri = "http://localhost:8080/last/points/{routeId}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("routeId", "1");
        String response = restTemplate.getForObject(uri, String.class, params);
        System.out.println(response);
    }*/

    private void getLastPoints() {
        final String uri = "http://localhost:8080/last/points/{routeId}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("routeId", "1");
        //String response = restTemplate.getForObject(uri, String.class, params);
        ResponseEntity<List<PointDTO>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<PointDTO>>() {
        },params);
        List<PointDTO> pointDTOList = response.getBody();
        pointDTOList.forEach(n ->print(n));
    }

    public void print(PointDTO pointDTO) {
        ObjectMapper mapper = new ObjectMapper();
        String toJson = "{}";
        try {
            toJson = mapper.writeValueAsString(pointDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(toJson);
    }
}
