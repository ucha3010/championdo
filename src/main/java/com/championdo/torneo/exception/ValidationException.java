package com.championdo.torneo.exception;

public class ValidationException extends GeneralException {

    public ValidationException(String adviceCode, String message) {
        super(adviceCode, message);
    }
}
