package com.oocl.easymovie.advice;

import com.oocl.easymovie.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler({UserNotFoundException.class,
            TheaterNotFoundException.class,
            MovieNotFoundException.class,
            OrderNotFoundException.class,
            ScheduleNotFoundException.class,
            CodeIllegalException.class,
            BalanceNotEnough.class,
            UserAuthenticationFailedException.class,
            UserAlreadyExistsException.class,
            SeatingNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(Exception exception) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse CodeIllegalException(Exception exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse BalanceNotEnough(Exception exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse UserAuthenticationFailedException(Exception exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse UserAlreadyExistsException(Exception exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

}
