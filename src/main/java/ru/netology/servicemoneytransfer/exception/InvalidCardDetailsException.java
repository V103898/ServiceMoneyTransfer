package ru.netology.servicemoneytransfer.exception;

public class InvalidCardDetailsException extends RuntimeException {
    public InvalidCardDetailsException(String message) {
        super(message);
    }
}