package com.apushkin.ironbank.controller;

import com.apushkin.ironbank.exception.NotEnoughMoneyException;
import com.apushkin.ironbank.model.Bank;
import com.apushkin.ironbank.model.dto.BankDto;
import com.apushkin.ironbank.model.dto.CreditDto;
import com.apushkin.ironbank.repository.BankRepository;
import com.apushkin.ironbank.service.MoneyService;
import com.apushkin.ironbank.service.ScoringService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
public class BankController {
    private final BankRepository bankRepository;
    private final MoneyService moneyService;
    private final ScoringService scoringService;

    public BankController(BankRepository bankRepository, MoneyService moneyService, ScoringService scoringService) {
        this.bankRepository = bankRepository;
        this.moneyService = moneyService;
        this.scoringService = scoringService;
    }

    @GetMapping("/amount")
    public BankDto getBank() {
        Bank bank = bankRepository.findAll().get(0);
        return new BankDto(bank.getName(), bank.getAmount());
    }

    @GetMapping("/credit")
    public CreditDto takeCredit(@RequestParam String client, @RequestParam Long amount) {
        if (scoringService.bankHasEnoughMoney(amount)) {
            if (scoringService.canTakeCredit(client)) {
                moneyService.takeCredit(amount);
                return new CreditDto(client, true, amount);
            }
        }
        return new CreditDto(client, false, 0L);
    }

    @ExceptionHandler(value = {NotEnoughMoneyException.class})
    public ResponseEntity<Object> handleNotEnoughMoneyException(NotEnoughMoneyException exception) {
        return ResponseEntity.internalServerError().body(exception.getMessage());
    }
}
