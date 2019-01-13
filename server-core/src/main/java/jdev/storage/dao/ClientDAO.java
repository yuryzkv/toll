package jdev.storage.dao;

import jdev.domain.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientDAO extends CrudRepository<Client, Integer> {
}
