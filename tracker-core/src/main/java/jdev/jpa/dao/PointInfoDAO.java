package jdev.jpa.dao;

import jdev.domain.PointInfo;
import org.springframework.data.repository.CrudRepository;

public interface PointInfoDAO extends CrudRepository<PointInfo,Integer> {
}
