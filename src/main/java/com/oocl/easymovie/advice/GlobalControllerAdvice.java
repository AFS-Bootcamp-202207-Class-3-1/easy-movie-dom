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
            CodeIllegalException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(Exception exception) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

}
