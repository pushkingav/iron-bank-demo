package com.apushkin.ironbank.service;

import com.apushkin.ironbank.exception.NotEnoughMoneyException;
import com.apushkin.ironbank.model.Bank;
import com.apushkin.ironbank.repository.BankRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MoneyService {
    private BankRepository bankRepository;
    private ScoringService scoringService;

    public MoneyService(BankRepository bankRepository, ScoringService scoringService) {
        this.bankRepository = bankRepository;
        this.scoringService = scoringService;
    }

    @Transactional
    public void takeCredit(Long amount) {
        //We need to check this once more if another client took a credit during current scoring session and
        //the Bank has not enough money for the current client
        if (!scoringService.bankHasEnoughMoney(amount)) {
            throw new NotEnoughMoneyException();
        }
        Bank bank = bankRepository.findAll().get(0);
        bank.setAmount(bank.getAmount() - amount);
        bankRepository.save(bank);
    }
}
