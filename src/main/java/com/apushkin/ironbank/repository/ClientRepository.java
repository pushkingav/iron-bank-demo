package com.apushkin.ironbank.repository;

import com.apushkin.ironbank.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Long> {
    List<Client> findByLastName(String lastName);

    Client findById(long id);
}
