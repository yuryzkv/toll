package jdev.dao;

import jdev.domain.Route;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RouteDAO extends CrudRepository<Route,Integer> {

    List<Route> findByNameAndRouteDevice(String name, String routeDevice);


}
