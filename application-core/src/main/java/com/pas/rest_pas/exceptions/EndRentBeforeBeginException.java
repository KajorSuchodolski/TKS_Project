package com.pas.rest_pas.exceptions;

import javax.ws.rs.ForbiddenException;

public class EndRentBeforeBeginException extends ForbiddenException {
    public EndRentBeforeBeginException() {
        super("Setting end rent date before begin date is forbidden.");
    }
}
