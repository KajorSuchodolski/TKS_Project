package com.pas.rest_pas.exceptions;
import java.time.DateTimeException;

public class WrongDateFormatException extends DateTimeException {
    public WrongDateFormatException() {
        super("Wrong date format!");
    }
}
