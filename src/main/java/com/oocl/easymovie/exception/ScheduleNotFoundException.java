package com.oocl.easymovie.exception;

public class ScheduleNotFoundException extends RuntimeException{
   public ScheduleNotFoundException(){
        super("schedule no found");
    }
}
