package org.ias.tks.appcore.exceptions;

import javax.validation.ValidationException;

public class EntityValidationException extends ValidationException {
    public EntityValidationException(String message) {
        super(message);
    }
}
