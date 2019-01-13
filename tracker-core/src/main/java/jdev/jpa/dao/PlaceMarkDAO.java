package jdev.jpa.dao;

import jdev.domain.PlaceMark;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlaceMarkDAO extends CrudRepository<PlaceMark,Integer> {

    List<PlaceMark> findByRouteIdOrderByRowTimeAsc(Integer routeId);
}
