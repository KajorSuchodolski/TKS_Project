package org.ias.tks.appcore.domainmodel.exceptions;
import java.time.DateTimeException;

public class WrongDateFormatException extends DateTimeException {
    public WrongDateFormatException() {
        super("Wrong date format!");
    }
}
