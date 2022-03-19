package com.pas.rest_pas.exceptions;
import java.time.DateTimeException;

public class DateInPastException extends DateTimeException {
    public DateInPastException() {
        super("The given date is in past!");
    }
}