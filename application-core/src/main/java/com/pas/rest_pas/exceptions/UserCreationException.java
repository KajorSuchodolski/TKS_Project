package com.pas.rest_pas.exceptions;
import javax.enterprise.inject.CreationException;

public class UserCreationException extends CreationException {
    public UserCreationException( String message) {
        super(message);
    }
}
