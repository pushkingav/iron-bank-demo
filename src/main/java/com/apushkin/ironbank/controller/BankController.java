package com.apushkin.ironbank.controller;

import com.apushkin.ironbank.model.Bank;
import com.apushkin.ironbank.model.dto.BankDto;
import com.apushkin.ironbank.repository.BankRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
    public static String template = "Money available: %s";

    private BankRepository bankRepository;

    public BankController(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @GetMapping("/amount")
    public BankDto getBank() {
        Bank bank = bankRepository.findAll().get(0);
        return new BankDto(bank.getName(), bank.getAmount());
    }
}
