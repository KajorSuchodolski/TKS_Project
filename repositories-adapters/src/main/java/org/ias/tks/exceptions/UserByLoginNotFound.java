package org.ias.tks.exceptions;

import javax.ws.rs.NotFoundException;

public class UserByLoginNotFound extends NotFoundException {
    public UserByLoginNotFound() {
        super("User with given login was not found!");
    }
}