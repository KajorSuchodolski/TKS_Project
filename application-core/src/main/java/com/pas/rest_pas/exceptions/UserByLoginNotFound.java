package com.pas.rest_pas.exceptions;

import javax.ws.rs.NotFoundException;

public class UserByLoginNotFound extends NotFoundException {
    public UserByLoginNotFound() {
        super("User with given login was not found!");
    }
}
