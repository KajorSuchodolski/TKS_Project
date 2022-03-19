package com.pas.rest_pas.exceptions;

import javax.validation.ValidationException;

public class EntityValidationException extends ValidationException {
    public EntityValidationException(String message) {
        super(message);
    }
}
