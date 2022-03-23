package org.ias.tks.appcore.appservices.endpoints;


import org.ias.tks.appcore.appservices.services.UserService;
import org.ias.tks.appcore.domainmodel.exceptions.*;
import org.ias.tks.appcore.domainmodel.model.user.User;
import org.ias.tks.appcore.domainmodel.model.user.access_levels.AccessLevelType;
import org.ias.tks.appcore.domainmodel.model.user.access_levels.Administrator;
import org.ias.tks.appcore.domainmodel.model.user.access_levels.Client;
import org.ias.tks.appcore.domainmodel.model.user.access_levels.Manager;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/user")
@ApplicationScoped
public class UserController {

    @Inject
    private UserService userService;

    // READ
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok().entity(userService.getAll()).build();
    }

    @GET
    @Path("/get-by-login")
    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin"})
    public Response getUserByLogin(@QueryParam("login") String login) {
        if(login == null || login.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Login parameter is empty!").build();
        }
        try {
            User user = userService.getUserByLogin(login);
            return Response.ok().entity(user).build();
        } catch(UserByLoginNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }

    }

    @GET
    @Path("/get-by-id")
    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin"})
    public Response getUserById( @QueryParam("id") String uuid ) {
        if(uuid == null || uuid.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Id parameter is empty!").build();
        }
        try {
            return Response.ok().entity(userService.getUserById(UUID.fromString(uuid))).build();
        } catch(UserByIdNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/search-by-login")
    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin"})
    public Response searchUsersByLogin(@QueryParam("login") String login) {
        if(login == null || login.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Login parameter is empty!").build();
        }
        return Response.ok().entity(userService.searchUsersByLogin(login)).build();
    }

    // CREATE
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin"})
    public Response addUser( User user) {
        if(user == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Given user is null").build();
        }
        try {
            userService.addUser(user);
            return Response.ok(Response.Status.CREATED)
                    .entity("User has been added")
                    .build();
        } catch(UserCreationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }


    // UPDATE
    @PUT
    @Path("/{login}/update")
    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin"})
    public Response updateUser( @PathParam("login") String login, User user, @NotNull @NotEmpty @HeaderParam("If-Match") String etag) {
        if(login == null || login.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Login parameter is empty").build();
        }
        if(user == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Given user is null").build();
        }
        try {
            switch(userService.getUserByLogin(login).getAccessLevel()) {
                case "Admin" -> user.setAccessLevel(new Administrator(AccessLevelType.ADMINISTRATOR));
                case "Manager" -> user.setAccessLevel(new Manager(AccessLevelType.MANAGER));
                default -> user.setAccessLevel(new Client(AccessLevelType.CLIENT));
            }
            userService.updateUser(login, user);
            return Response.ok()
                    .entity("User updated successfully")
                    .build();
        } catch(EntityValidationException | UserUpdateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{login}/activate")
    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin"})
    public Response activateUser(@PathParam("login") String login) {
        if(login == null || login.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Login parameter is empty").build();
        }
        try {
            userService.activateUser(login);
            return Response.ok()
                    .entity("User activated")
                    .build();
        } catch(UserByLoginNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{login}/deactivate")
    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin"})
    public Response deactivateUser(@PathParam("login") String login) {
        if(login == null || login.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Login parameter is empty").build();
        }
        try {
            userService.deactivateUser(login);
            return Response.ok()
                    .entity("User deactivated")
                    .build();
        } catch(UserByLoginNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
