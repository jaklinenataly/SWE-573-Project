package com.vakses.exception;

/**
 * Created by veraxmedax on 23/03/2018.
 */
public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String message) {
        super(message);
    }
}
