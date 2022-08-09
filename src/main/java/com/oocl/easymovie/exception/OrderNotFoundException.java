package com.oocl.easymovie.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(){
        super("order not found");
    }
}
