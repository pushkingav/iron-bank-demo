package com.apushkin.ironbank.repository;

import com.apushkin.ironbank.model.Bank;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BankRepository extends CrudRepository<Bank, Long> {
    List<Bank> findAll();
}
