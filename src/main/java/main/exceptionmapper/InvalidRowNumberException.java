package main.exceptionmapper;

import jakarta.ws.rs.BadRequestException;

public class InvalidRowNumberException extends BadRequestException {
    public InvalidRowNumberException(String s) {
        super(s);
    }
}
