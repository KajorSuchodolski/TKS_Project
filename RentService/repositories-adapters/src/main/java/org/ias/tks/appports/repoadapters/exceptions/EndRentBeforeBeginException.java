package org.ias.tks.appports.repoadapters.exceptions;

import javax.ws.rs.ForbiddenException;

public class EndRentBeforeBeginException extends ForbiddenException {
    public EndRentBeforeBeginException() {
        super("Setting end rent date before begin date is forbidden.");
    }
}
