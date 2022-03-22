package org.ias.tks.appcore.domainmodel.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class EntityCreationException extends WebApplicationException {
    public EntityCreationException() {
        super(Response.status(Response.Status.BAD_REQUEST).entity("The given attribute(s) are invalid!").build());
    }
}
