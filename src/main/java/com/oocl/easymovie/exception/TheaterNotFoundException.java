package com.oocl.easymovie.exception;

public class TheaterNotFoundException extends RuntimeException{
    public TheaterNotFoundException() {
        super("Theater not found");
    }
}
