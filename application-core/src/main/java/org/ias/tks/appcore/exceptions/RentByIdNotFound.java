package org.ias.tks.appcore.exceptions;

import javax.ws.rs.NotFoundException;

public class RentByIdNotFound extends NotFoundException {
    public RentByIdNotFound() {
        super("Rent with given ID was not found!");
    }
}
