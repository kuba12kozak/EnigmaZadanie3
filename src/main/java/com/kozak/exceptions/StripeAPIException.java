package com.kozak.exceptions;

public class StripeAPIException extends Exception {
    public StripeAPIException(String message) {
        super(message);
    }
}
