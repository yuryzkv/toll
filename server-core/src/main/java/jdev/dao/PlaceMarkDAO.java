package jdev.dao;

import jdev.domain.PlaceMark;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlaceMarkDAO extends CrudRepository<PlaceMark,Integer> {

    List<PlaceMark> findByRouteIdOrderByRowTimeAsc(Integer routeId);

    List<PlaceMark> findByLongitudeAndLatitudeAndTime(Double longitude,Double latitude,Long time);

    }
