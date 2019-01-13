package jdev.dao;

import jdev.domain.Manager;
import org.springframework.data.repository.CrudRepository;

public interface ManagerDAO extends CrudRepository<Manager,Integer> {
}
