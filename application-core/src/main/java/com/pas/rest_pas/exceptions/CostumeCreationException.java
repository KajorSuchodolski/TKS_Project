package com.pas.rest_pas.exceptions;

import javax.enterprise.inject.CreationException;

public class CostumeCreationException extends CreationException {
    public CostumeCreationException(String message) {
        super(message);
    }
}
