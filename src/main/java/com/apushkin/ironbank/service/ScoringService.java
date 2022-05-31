package com.apushkin.ironbank.service;

import com.apushkin.ironbank.repository.BankRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Random;

@Service
public class ScoringService {
    @Autowired
    private BankRepository bankRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ScoringService.class);

    @Transactional
    public boolean canTakeCredit(String clientName) {
        if (clientName.toLowerCase(Locale.ROOT).contains("stark")) {
            LOGGER.info(String
                    .format("We cannot give a credit to %s since he will not survive the winter", clientName));
            return false;
        }

        Random random = new Random();
        int score = (int) (random.nextDouble() * 100);
        LOGGER.info(String.format("The client %s got the score of %d", clientName, score));
        return score > 50;
    }

    @Transactional
    public boolean bankHasEnoughMoney(long amount) {
        Long moneyAvailable = bankRepository.findAll().get(0).getAmount();
        if (moneyAvailable < amount) {
            LOGGER.info(String
                    .format("The bank currently has %d money and someone expected %d", moneyAvailable, amount));
            return false;
        }
        return true;
    }
}
