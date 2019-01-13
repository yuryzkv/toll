package jdev.service;

import jdev.domain.PlaceMark;
import jdev.domain.Route;
import jdev.dto.PointDTO;
import jdev.jpa.LandMark;
import jdev.jpa.Track;
import jdev.jpa.dao.PlaceMarkDAO;
import jdev.jpa.dao.PointInfoDAO;
import jdev.jpa.dao.RouteDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@EnableJpaRepositories("jdev.jpa.dao")
public class StoreService {

    private static final Logger log = LoggerFactory.getLogger(StoreService.class);

    @Autowired
    public RouteDAO routeDAO;

    @Autowired
    public PlaceMarkDAO placeMarkDAO;

    @Autowired
    public PointInfoDAO pointInfoDAO;

    public static  StoreService getInstance() {
        return new StoreService();
    }

    public void saveTrack(Track track) {
        List<Route> routes = routeDAO.findByNameAndRouteDevice(track.getTrackName(), track.getDevId());
        if (routes == null | (routes != null & routes.size() == 0)) {
            Route route = new Route();
            route.setName(track.getTrackName());
            route.setRouteDevice(track.getDevId());
            route = routeDAO.save(route);
            log.info("Route have saved with id=" + route.getRouteId());
            log.info("List track's is size = "+track.getTrackPoints().size());
            for (LandMark landMark : track.getTrackPoints()) {
                PlaceMark placeMark = new PlaceMark();
                placeMark.setRouteId(route.getRouteId());
                placeMark.setLatitude(landMark.getLat());
                placeMark.setLongitude(landMark.getLon());
                placeMark.setTime(landMark.getCurren_time());
                placeMark.setRowTime(new Timestamp(System.currentTimeMillis()));
                placeMark = placeMarkDAO.save(placeMark);
                log.info("PlaceMark have saved with id=" + placeMark.getPlacemarkId());
            }
        }
    }

    public List<Track> readAllTrack(){
        ArrayList<Track> routeList = new ArrayList<Track>();
        List<Route> routes =  (List<Route>) routeDAO.findAll();
        if(routes != null && routes.size()>0) {
            log.info("The list Route's is "+routes.size());
            for (Route route : routes) {
                Track track = new Track();
                track.setTrackName(route.getName());
                track.setDevId(route.getRouteDevice());
                routeList.add(track);
            }
        }
        return routeList;
    }

    public List<PointDTO> readTrack(Track track){
        ArrayList<PointDTO> pointDTOList = new ArrayList<PointDTO>();

        List<Route> routes = routeDAO.findByNameAndRouteDevice(track.getTrackName(), track.getDevId());
        if (routes != null && routes.size() > 0) {
            Route route = routes.get(0);
            List<PlaceMark> placeMarks = placeMarkDAO.findByRouteIdOrderByRowTimeAsc(route.getRouteId());
            for(PlaceMark placeMark : placeMarks){
                PointDTO pointDTO = new PointDTO();
                pointDTO.setLon(placeMark.getLongitude());
                pointDTO.setLat(placeMark.getLatitude());
                pointDTO.setTime(placeMark.getTime());
                pointDTO.setRoute_name(route.name);
                pointDTO.setAutoId(route.routeDevice);
                pointDTOList.add(pointDTO);
            }
        }
        return pointDTOList;
    }
}
