package org.ias.tks.appcore.domainmodel.exceptions;

import javax.ws.rs.NotFoundException;

public class UserByIdNotFound extends NotFoundException {

    public UserByIdNotFound() {
        super("User with given login was not found!");
    }
}
