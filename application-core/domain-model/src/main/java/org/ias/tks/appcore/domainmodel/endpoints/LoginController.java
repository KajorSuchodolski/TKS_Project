package org.ias.tks.appcore.domainmodel.endpoints;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.ias.tks.appcore.exceptions.UserByLoginNotFound;
import org.ias.tks.appcore.exceptions.UserUpdateException;
import org.ias.tks.appcore.services.UserService;
import org.ias.tks.appcore.domainmodel.security.InMemoryIdentityStore;
import org.ias.tks.appcore.domainmodel.security.JWTMechanism;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("authenticate")
@ApplicationScoped
public class LoginController {

    public LoginController() {
    }

    @Inject
    private InMemoryIdentityStore identityStoreHandler;

    @Inject
    private UserService userManager;

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces
    public Response authenticate(@NotNull String body) {
        try {
            JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();
            String login = jsonObject.get("login").getAsString();
            System.out.println(login);
            String password = jsonObject.get("password").getAsString();
            System.out.println(password);
            Credential credential = new UsernamePasswordCredential(login, password);
            CredentialValidationResult result = identityStoreHandler.validate(credential);
            if (result.getStatus() == CredentialValidationResult.Status.VALID) {
                return Response.accepted()
                        .type("application/jwt")
                        .entity(JWTMechanism.generateJWT(result))
                        .build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (JsonSyntaxException | NullPointerException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid json name").build();
        }
    }

    @POST
    @Path("{login}/change-password")
    @RolesAllowed({"Admin", "Manager", "Client"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeUserPassword( @PathParam("login") String login, String body) {
        JsonObject jsonBody = JsonParser.parseString(body).getAsJsonObject();
        String newPassword = jsonBody.get("password").getAsString();
        try {
            System.out.println(login + "      " + newPassword);
            userManager.updateUserPassword(login, newPassword);
            return Response.ok().build();
        } catch (UserByLoginNotFound | UserUpdateException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e).build();
        }
    }

}
