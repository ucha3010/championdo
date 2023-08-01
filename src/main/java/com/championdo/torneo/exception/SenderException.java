package com.championdo.torneo.exception;

public class SenderException extends GeneralException {

    public SenderException(String adviceCode, String message) {
        super(adviceCode, message);
    }
}
