package com.pas.rest_pas.endpoints;

import com.pas.rest_pas.exceptions.*;
import com.pas.rest_pas.managers.RentManager;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/rent")
@ApplicationScoped
public class RentController {

    @Inject
    private RentManager rentManager;

    // CREATE
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Admin", "Client"})
    public Response addRent( @QueryParam("login") String login, @QueryParam("date") String date, List<UUID> costumeIds) {
        if(login == null || login.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Login parameter is empty").build();
        }
        if(date == null || date.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Date parameter is empty").build();
        }
        if(costumeIds == null || costumeIds.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Given list is empty").build();
        }
        try {
            rentManager.addRent(login, costumeIds, date);
            return Response.ok(Response.Status.CREATED)
                    .entity("Rent added successfully")
                    .build();
        } catch(CostumeInUseException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        } catch(CostumeByIdNotFound | UserByLoginNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch(DateInPastException | WrongDateFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // READ
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Admin", "Client"})
    public Response getAll() {
        return Response.ok().entity(rentManager.getAll()).build();
    }

    @GET
    @Path("/all-current-rents")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Admin", "Client"})
    public Response getAllCurrent() {
        return Response.ok().entity(rentManager.getAllCurrent()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Admin", "Client"})
    public Response getRentById(@PathParam("id") String rentId) {
        if(rentId == null || rentId.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("RentID parameter is empty").build();
        }
        try {
            return Response.ok()
                    .entity(rentManager.getRentById(UUID.fromString(rentId)))
                    .build();
        } catch(RentByIdNotFound e){
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{login}/current-rents")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Admin", "Client"})
    public Response userCurrentRents(@PathParam("login") String login) {
        if(login == null || login.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Login parameter is empty").build();
        } try {
            return Response.ok().entity(rentManager.userCurrentRents(login)).build();
        } catch(UserByLoginNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }

    }

    @GET
    @Path("/{login}/past-rents")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Admin", "Client"})
    public Response userPastRents(@PathParam("login") String login)  {
        if(login == null || login.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Login parameter is empty").build();
        } try {
            return Response.ok().entity(rentManager.userPastRents(login)).build();
        } catch(UserByLoginNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/costume-allocations")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Admin", "Manager", "Client"})
    public Response getCostumeAllocations(@QueryParam("id") String costumeId) {
        if(costumeId == null || costumeId.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("RentID parameter is empty").build();
        }
        try {
            return Response.ok(Response.Status.OK)
                    .entity(rentManager.getCostumeAllocations(UUID.fromString(costumeId)))
                    .build();
        } catch(CostumeByIdNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }


    // DELETE
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Admin", "Client"})
    public Response deleteRent(@PathParam("id") String rentId)  {
        if(rentId == null || rentId.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("RentID parameter is empty").build();
        }
        try {
            rentManager.deleteRentFromRepo(UUID.fromString(rentId));
            return Response.ok(Response.Status.OK)
                    .entity("Rent removed successfully")
                    .build();
        } catch(RentByIdNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    /*
    *
    * TODO
    *  CHANGE FUNCTIONALITY FROM REPO TO MANAGER (I AM SAYING ABOUT MOVING REPO BODY FUNCTION INTO THE MANAGER)
    *  */

    @PUT
    @Path("/{id}/end")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Admin", "Client"})
    public Response endRent(@PathParam("id") String rentId, @QueryParam("date") String date) throws RentByIdNotFound{
        if(rentId == null || rentId.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("RentID parameter is empty").build();
        }
        if(date == null || date.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Date parameter is empty").build();
        }
        try {
            rentManager.endRent(date, UUID.fromString(rentId));
            return Response.ok(Response.Status.OK)
                    .entity("Rent was ended successfully")
                    .build();
        } catch(RentByIdNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch(EndRentBeforeBeginException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        }
    }
}
