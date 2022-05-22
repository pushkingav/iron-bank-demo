package com.apushkin.ironbank.model.dto;

public class BankDto {
    private final String name;
    private final Long moneyAvailable;

    public BankDto(String name, Long moneyAvailable) {
        this.name = name;
        this.moneyAvailable = moneyAvailable;
    }

    public String getName() {
        return name;
    }

    public Long getMoneyAvailable() {
        return moneyAvailable;
    }


}
