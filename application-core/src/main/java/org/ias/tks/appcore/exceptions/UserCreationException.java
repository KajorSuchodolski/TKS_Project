package org.ias.tks.appcore.exceptions;
import javax.enterprise.inject.CreationException;

public class UserCreationException extends CreationException {
    public UserCreationException( String message) {
        super(message);
    }
}
