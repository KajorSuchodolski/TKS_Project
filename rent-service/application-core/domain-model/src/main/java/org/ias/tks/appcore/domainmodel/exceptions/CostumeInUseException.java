package org.ias.tks.appcore.domainmodel.exceptions;

import javax.naming.directory.AttributeInUseException;


public class CostumeInUseException extends AttributeInUseException {
    public CostumeInUseException() {
        super("Costume is already in use!");
    }
}
