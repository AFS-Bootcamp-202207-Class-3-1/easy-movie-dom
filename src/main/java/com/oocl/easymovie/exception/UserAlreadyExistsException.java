package com.oocl.easymovie.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(){super("User name already exists");}
}
