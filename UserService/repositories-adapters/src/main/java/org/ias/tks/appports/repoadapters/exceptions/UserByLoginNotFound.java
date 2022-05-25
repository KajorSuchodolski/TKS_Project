package org.ias.tks.appports.repoadapters.exceptions;

import javax.ws.rs.NotFoundException;

public class UserByLoginNotFound extends NotFoundException {
    public UserByLoginNotFound() {
        super("User with given login was not found!");
    }
}
