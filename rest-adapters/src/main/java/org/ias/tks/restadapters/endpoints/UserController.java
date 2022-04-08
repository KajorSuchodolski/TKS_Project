package org.ias.tks.restadapters.endpoints;

import org.ias.tks.appcore.domainmodel.exceptions.EntityValidationException;
import org.ias.tks.appcore.domainmodel.exceptions.UserByIdNotFound;
import org.ias.tks.appcore.domainmodel.exceptions.UserByLoginNotFound;
import org.ias.tks.appcore.domainmodel.exceptions.UserCreationException;
import org.ias.tks.restadapters.dto.user.UserInputDto;
import org.ias.tks.restadapters.dto.user.UserOutputDto;
import org.ias.tks.restadapters.ports.UserRestPorts;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/user")
@ApplicationScoped
public class UserController {

    @Inject
    private UserRestPorts userRestPorts;

    // READ
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.ok().entity(userRestPorts.getAll()).build();
    }

    @GET
    @Path("/get-by-login")
    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin"})
    public Response getUserByLogin(@QueryParam("login") String login) {
        if (login == null || login.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Login parameter is empty!").build();
        }
        try {
            UserOutputDto user = userRestPorts.getUserByLogin(login);
            return Response.ok().entity(user).build();
        } catch (UserByLoginNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }

    }

    @GET
    @Path("/get-by-id")
    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin"})
    public Response getUserById(@QueryParam("id") String uuid) {
        if (uuid == null || uuid.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Id parameter is empty!").build();
        }
        try {
            return Response.ok().entity(userRestPorts.getUserById(UUID.fromString(uuid))).build();
        } catch (UserByIdNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/search-by-login")
    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin"})
    public Response searchUsersByLogin(@QueryParam("login") String login) {
        if (login == null || login.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Login parameter is empty!").build();
        }
        return Response.ok().entity(userRestPorts.searchUsersByLogin(login)).build();
    }

    // CREATE
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin"})
    public Response addUser(UserInputDto user) {
        if (user == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Given user is null").build();
        }
        try {
            userRestPorts.addUser(user);
            return Response.ok(Response.Status.CREATED)
                    .entity("User has been added")
                    .build();
        } catch (UserCreationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }


    // UPDATE
    @PUT
    @Path("/{login}/update")
    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin"})
    public Response updateUser(@PathParam("login") String login, UserInputDto user) {
        if (login == null || login.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Login parameter is empty").build();
        }
        if (user == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Given user is null").build();
        }
        try {
            switch (userRestPorts.getUserByLogin(login).getAccessLevel()) {
                case "Admin" -> user.setAccessLevel("Admin");
                case "Manager" -> user.setAccessLevel("Manager");
                default -> user.setAccessLevel("Client");
            }
            userRestPorts.updateUser(login, user);
            return Response.ok()
                    .entity("User updated successfully")
                    .build();
        } catch (EntityValidationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{login}/activate")
    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin"})
    public Response activateUser(@PathParam("login") String login) {
        if (login == null || login.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Login parameter is empty").build();
        }
        try {
            userRestPorts.activateUser(login);
            return Response.ok()
                    .entity("User activated")
                    .build();
        } catch (UserByLoginNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{login}/deactivate")
    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin"})
    public Response deactivateUser(@PathParam("login") String login) {
        if (login == null || login.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Login parameter is empty").build();
        }
        try {
            userRestPorts.deactivateUser(login);
            return Response.ok()
                    .entity("User deactivated")
                    .build();
        } catch (UserByLoginNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
