package com.apushkin.ironbank.exception;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException() {
        super("Not enough money");
    }
}
