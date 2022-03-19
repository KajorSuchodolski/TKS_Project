package com.pas.rest_pas.exceptions;
import javax.ws.rs.ForbiddenException;


public class UserInactiveException extends ForbiddenException {
    public UserInactiveException() {
        super("User is inactive");
    }
}
