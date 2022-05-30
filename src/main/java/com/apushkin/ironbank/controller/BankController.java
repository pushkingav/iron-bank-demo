package com.apushkin.ironbank.controller;

import com.apushkin.ironbank.model.Bank;
import com.apushkin.ironbank.model.dto.BankDto;
import com.apushkin.ironbank.model.dto.CreditDto;
import com.apushkin.ironbank.repository.BankRepository;
import com.apushkin.ironbank.service.ScoringService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
    private BankRepository bankRepository;
    private ScoringService scoringService;

    public BankController(BankRepository bankRepository, ScoringService scoringService) {
        this.bankRepository = bankRepository;
        this.scoringService = scoringService;
    }

    @GetMapping("/amount")
    public BankDto getBank() {
        Bank bank = bankRepository.findAll().get(0);
        return new BankDto(bank.getName(), bank.getAmount());
    }

    @GetMapping("/credit")
    public CreditDto takeCredit(@RequestParam String client, @RequestParam Long amount) {
        if (scoringService.canTakeCredit(client, amount)) {
            Bank bank = bankRepository.findAll().get(0);
            bank.setAmount(bank.getAmount() - amount);
            bankRepository.save(bank);
            return new CreditDto(client, true, amount);
        }
        return new CreditDto(client, false, 0L);
    }
}
