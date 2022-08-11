package com.oocl.easymovie.exception;

public class CodeIllegalException extends RuntimeException{
    public CodeIllegalException() {
        super("Code is wrong");
    }
}
