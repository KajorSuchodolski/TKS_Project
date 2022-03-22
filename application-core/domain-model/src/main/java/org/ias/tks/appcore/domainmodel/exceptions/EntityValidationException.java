package org.ias.tks.appcore.domainmodel.exceptions;

import javax.validation.ValidationException;

public class EntityValidationException extends ValidationException {
    public EntityValidationException(String message) {
        super(message);
    }
}
