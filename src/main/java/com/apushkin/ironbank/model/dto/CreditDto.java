package com.apushkin.ironbank.model.dto;

public class CreditDto {
    private final String client;
    private final Boolean approved;
    private final Long amount;

    public CreditDto(String client, Boolean approved, Long amount) {
        this.client = client;
        this.approved = approved;
        this.amount = amount;
    }

    public String getClient() {
        return client;
    }

    public Boolean getApproved() {
        return approved;
    }

    public Long getAmount() {
        return amount;
    }
}
