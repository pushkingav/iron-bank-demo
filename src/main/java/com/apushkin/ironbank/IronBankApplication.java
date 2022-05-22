package com.apushkin.ironbank;

import com.apushkin.ironbank.model.Bank;
import com.apushkin.ironbank.model.Client;
import com.apushkin.ironbank.repository.BankRepository;
import com.apushkin.ironbank.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IronBankApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(IronBankApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(IronBankApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ClientRepository repository, BankRepository bankRepository) {
        return args -> {
            //create the Bank
            bankRepository.save(new Bank("Iron Bank", 100500L));

            // save a few Clients
            repository.save(new Client("Jack", "Bauer"));
            repository.save(new Client("Chloe", "O'Brian"));
            repository.save(new Client("Kim", "Bauer"));
            repository.save(new Client("David", "Palmer"));
            repository.save(new Client("Michelle", "Dessler"));

            // fetch all clients
            LOGGER.info("Clients found with findAll():");
            LOGGER.info("-------------------------------");
            for (Client client : repository.findAll()) {
                LOGGER.info(client.toString());
            }
            LOGGER.info("");

            // fetch an individual client by ID
            Client client = repository.findById(1L);
            LOGGER.info("Client found with findById(1L):");
            LOGGER.info("--------------------------------");
            LOGGER.info(client.toString());
            LOGGER.info("");

            // fetch clients by last name
            LOGGER.info("Client found with findByLastName('Bauer'):");
            LOGGER.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> {
                LOGGER.info(bauer.toString());
            });
            // for (Client bauer : repository.findByLastName("Bauer")) {
            //  log.info(bauer.toString());
            // }
            LOGGER.info("");
        };
    }

}
