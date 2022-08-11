package com.oocl.easymovie.exception;

public class UserAuthenticationFailedException extends RuntimeException {
    public UserAuthenticationFailedException(){super("User name or password error");}
}
