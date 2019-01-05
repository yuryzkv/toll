package jdev.storage.dao;

import jdev.storage.Manager;
import org.springframework.data.repository.CrudRepository;

public interface ManagerDAO extends CrudRepository<Manager,Integer> {
}
