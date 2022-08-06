package com.oocl.easymovie.exception;

/**
 * @author edward
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found");
    }
}
