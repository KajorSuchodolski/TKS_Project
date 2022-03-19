package com.pas.rest_pas.exceptions;

import javax.ws.rs.NotFoundException;

public class CostumeByIdNotFound extends NotFoundException {
    public CostumeByIdNotFound() {
        super("Costume with given ID was not found!");
    }
}
