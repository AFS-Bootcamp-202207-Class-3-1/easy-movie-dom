package com.oocl.easymovie.exception;

public class SeatingNotFoundException extends RuntimeException {
    public SeatingNotFoundException() {
        super("Seating Not found");
    }
}
