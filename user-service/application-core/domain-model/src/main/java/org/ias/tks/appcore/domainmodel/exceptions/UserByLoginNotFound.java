package org.ias.tks.appcore.domainmodel.exceptions;

import javax.ws.rs.NotFoundException;

public class UserByLoginNotFound extends NotFoundException {
    public UserByLoginNotFound() {
        super("User with given login was not found!");
    }
}
