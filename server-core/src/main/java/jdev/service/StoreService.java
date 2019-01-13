package jdev.service;

import jdev.dao.ClientDAO;
import jdev.dao.ManagerDAO;
import jdev.dao.PlaceMarkDAO;
import jdev.dao.RouteDAO;
import jdev.domain.PlaceMark;
import jdev.domain.Route;
import jdev.dto.PointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class StoreService {
    @Autowired
    public PlaceMarkDAO placeMarkDAO;
    @Autowired
    public RouteDAO routeDAO;
    @Autowired
    public ClientDAO clientDAO;
    @Autowired
    public ManagerDAO managerDAO;

    public void savePoint(PointDTO pointDTO){
        Integer routeId;
        List<Route> listRoute = routeDAO.findByNameAndRouteDevice(pointDTO.getRoute_name(),pointDTO.getAutoId());
        if(listRoute == null || listRoute.size()==0){
            Route route = new Route();
            route.setName(pointDTO.getRoute_name());
            route.setRouteDevice(pointDTO.getAutoId());
            route = routeDAO.save(route);
            routeId = route.getRouteId();
        }else{
                routeId = listRoute.get(0).getRouteId();
        }

        List<PlaceMark> placeMarks = placeMarkDAO.findByLongitudeAndLatitudeAndTime(pointDTO.getLon(),pointDTO.getLat(),pointDTO.getTime());
        if(placeMarks == null || placeMarks.size() ==0){
            PlaceMark placeMark = new PlaceMark();
            placeMark.setLongitude(pointDTO.getLon());
            placeMark.setLatitude(pointDTO.getLat());
            placeMark.setTime(pointDTO.getTime());
            placeMark.setRouteId(routeId);
            placeMark.setRowTime(new Timestamp(System.currentTimeMillis()));
            placeMarkDAO.save(placeMark);
        }

    }


}
