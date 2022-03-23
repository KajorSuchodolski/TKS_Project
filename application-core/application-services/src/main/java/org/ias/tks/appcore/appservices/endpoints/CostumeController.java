package org.ias.tks.appcore.appservices.endpoints;

import org.ias.tks.appcore.appservices.services.CostumeService;
import org.ias.tks.appcore.domainmodel.exceptions.CostumeByIdNotFound;
import org.ias.tks.appcore.domainmodel.exceptions.CostumeCreationException;
import org.ias.tks.appcore.domainmodel.exceptions.CostumeInUseException;
import org.ias.tks.appcore.domainmodel.exceptions.EntityValidationException;
import org.ias.tks.appcore.domainmodel.model.costume.Costume;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/costume")
@ApplicationScoped
public class CostumeController {

    @Inject
    private CostumeService costumeService;

    // READ
    @GET
    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin", "Manager"})
    public Response getAll() {
        return Response.ok().entity(costumeService.getAll()).build();
    }

    @GET
    @Path("/all-rented")
    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin", "Manager", "Client"})
    public Response getAllRented() {
        return Response.ok().entity(costumeService.getAllByRentStatus(true)).build();
    }

    @GET
    @Path("/all-available")
    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin", "Manager", "Client"})
    public Response getAllAvailable() {
        return Response.ok().entity(costumeService.getAllByRentStatus(false)).build();
    }

    @GET
    @Path("/all-by-age")
    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin", "Manager"})
    public Response getAllCostumesByAge( @QueryParam("age") String age ) {
        if(age == null || age.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Age parameter is empty").build();
        }
        try {
            return Response.ok().entity(costumeService.getAllCostumesByAge(age)).build();
        } catch(EntityValidationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin", "Manager"})
    public Response getCostumeById( @PathParam("id") String uuid ) {
        if(uuid == null || uuid.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Id parameter is empty").build();
        }
        try {
            Costume costumeFound = costumeService.getCostumeById(UUID.fromString(uuid));
            return Response.ok(costumeFound).build();
        } catch(CostumeByIdNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/search-by-name")
    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin", "Manager"})
    public Response searchAllCostumesByName(@QueryParam("name") String name) {
        if(name == null || name.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Name parameter is empty").build();
        }
        return Response.ok().entity(costumeService.searchAllCostumesByName(name)).build();
    }

    @GET
    @Path("/costumes-by-params")
    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin", "Manager"})
    public Response getAllCostumesByParams(@QueryParam("age") String age, @QueryParam("size") String size) {
        if(age == null || age.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Age parameter is empty").build();
        }
        if(size == null || size.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Size parameter is empty").build();
        }
        try {
            return Response.ok().entity(costumeService.getAllCostumesByParams(age, size)).build();
        } catch(EntityValidationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // CREATE
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin", "Manager"})
    public Response addCostume( Costume costume) {
        if(costume == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Given costume is null").build();
        }
        try {
            costumeService.addCostume(costume);
            return Response.ok(Response.Status.CREATED)
                    .entity("Costume added!")
                    .build();
        } catch(CostumeCreationException | EntityValidationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // UPDATE

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin", "Manager"})
    public Response updateCostume( @PathParam("id") String id, Costume costume, @NotNull @NotEmpty @HeaderParam("If-Match") String etag) throws CostumeByIdNotFound {
        if(id == null || id.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Id parameter is empty").build();
        }
        if(costume == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Given costume is null").build();
        }
        try {
            costumeService.updateCostume(UUID.fromString(id), costume);
            return Response.ok(Response.Status.OK)
                    .entity("Costume updated successfully")
                    .build();
        } catch(CostumeByIdNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch(EntityValidationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch(IllegalArgumentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

//    @PUT
//    @Path("/{id}/activate")
//    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin", "Manager"})
//    public Response activateRent(@PathParam("id") String id) {
//        if(id == null || id.trim().equals("")) {
//            return Response.status(Response.Status.BAD_REQUEST).entity("Id parameter is empty").build();
//        }
//        try {
//            costumeService.activateRent(UUID.fromString(id));
//            return Response.ok(Response.Status.OK)
//                    .entity("Rent activated")
//                    .build();
//        } catch(CostumeByIdNotFound e) {
//            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
//        }
//    }
//
//    @PUT
//    @Path("/{id}/deactivate")
//    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin", "Manager"})
//    public Response deactivateRent(@PathParam("id") String id) {
//        if(id == null || id.trim().equals("")) {
//            return Response.status(Response.Status.BAD_REQUEST).entity("Id parameter is empty").build();
//        }
//        try {
//            costumeService.deactivateRent(UUID.fromString(id));
//            return Response.ok(Response.Status.OK)
//                    .entity("Rent activated")
//                    .build();
//        } catch(CostumeByIdNotFound e) {
//            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
//        }
//    }

    // DELETE
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin", "Manager"})
    public Response removeRent(@PathParam("id") String id) {
        if(id == null || id.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Id parameter is empty").build();
        }
        try {
            costumeService.removeCostume(UUID.fromString(id));
            return Response.ok(Response.Status.OK)
                    .entity("Rent removed")
                    .build();
        } catch(CostumeByIdNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch(CostumeInUseException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        }
    }




}
