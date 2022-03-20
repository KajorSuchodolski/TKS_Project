package org.ias.tks.appcore.exceptions;

import javax.enterprise.inject.CreationException;

public class CostumeCreationException extends CreationException {
    public CostumeCreationException(String message) {
        super(message);
    }
}
