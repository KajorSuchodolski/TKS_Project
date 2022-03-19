package com.pas.rest_pas.exceptions;

import javax.ws.rs.NotFoundException;

public class UserByIdNotFound extends NotFoundException {

    public UserByIdNotFound() {
        super("User with given login was not found!");
    }
}
