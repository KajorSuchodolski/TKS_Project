package org.ias.tks.appports.repoadapters.exceptions;

import javax.naming.directory.AttributeInUseException;


public class CostumeInUseException extends AttributeInUseException {
    public CostumeInUseException() {
        super("Costume is already in use!");
    }
}
