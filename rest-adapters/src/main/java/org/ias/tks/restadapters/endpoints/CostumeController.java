package org.ias.tks.restadapters.endpoints;//package org.ias.tks.appcore.domainmodel.endpoints;


//import org.ias.tks.appcore.appservices.services.CostumeService;
import org.ias.tks.appcore.domainmodel.exceptions.CostumeByIdNotFound;
import org.ias.tks.appcore.domainmodel.exceptions.CostumeCreationException;
import org.ias.tks.appcore.domainmodel.exceptions.EntityValidationException;
//import org.ias.tks.appcore.domainmodel.filter.SignatureVerifier;
import org.ias.tks.appcore.domainmodel.model.costume.Costume;
import org.ias.tks.appports.application.costume.CreateCostumeUseCase;
import org.ias.tks.appports.application.costume.GetCostumeUseCase;
import org.ias.tks.appports.application.costume.UpdateCostumeUseCase;
import org.ias.tks.appports.application.costume.RemoveCostumeUseCase;
import org.ias.tks.restadapters.dto.costume.CostumeDTO;
import org.ias.tks.restadapters.mappers.CostumeMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/costume")
@ApplicationScoped
public class CostumeController {

    @Inject
    private GetCostumeUseCase getCostumeUseCase;

    @Inject
    private CreateCostumeUseCase createCostumeUsecase;

    @Inject
    private UpdateCostumeUseCase updateCostumeUseCase;

    @Inject
    private RemoveCostumeUseCase removeCostumeUseCase;

    @Inject
    private CostumeMapper costumeMapper;
    // READ
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed({"Admin", "ManagerDTO"})
    public Response getAll() {
        return Response.ok().entity(getCostumeUseCase.getAll()).build();
    }

    @GET
    @Path("/all-rented")
    @Produces(MediaType.APPLICATION_JSON)
   // @RolesAllowed({"Admin", "ManagerDTO", "ClientDTO"})
    public Response getAllRented() {
        return Response.ok().entity(getCostumeUseCase.getAllByRentStatus(true)).build();
    }

    @GET
    @Path("/all-available")
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed({"Admin", "ManagerDTO", "ClientDTO"})
    public Response getAllAvailable() {
        return Response.ok().entity(getCostumeUseCase.getAllByRentStatus(false)).build();
    }

    @GET
    @Path("/all-by-age")
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed({"Admin", "ManagerDTO"})
    public Response getAllCostumesByAge( @QueryParam("age") String age ) {
        if(age == null || age.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Age parameter is empty").build();
        }
        try {
            return Response.ok().entity(getCostumeUseCase.getAllCostumesByAge(age)).build();
        } catch(EntityValidationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
//    @RolesAllowed({"Admin", "ManagerDTO"})
    public Response getCostumeById( @PathParam("id") String uuid ) {
        if(uuid == null || uuid.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Id parameter is empty").build();
        }
        try {
            Costume costumeFound = getCostumeUseCase.getCostumeById(UUID.fromString(uuid));
            return Response.ok(costumeFound).build();
//                    .header("Etag", SignatureVerifier.calculateEntitySignature(costumeFound)).build();
        } catch(CostumeByIdNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/search-by-name")
    @Produces(MediaType.APPLICATION_JSON)
   //@RolesAllowed({"Admin", "ManagerDTO"})
    public Response searchAllCostumesByName(@QueryParam("name") String name) {
        if(name == null || name.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Name parameter is empty").build();
        }
        return Response.ok().entity(getCostumeUseCase.searchAllCostumesByName(name)).build();
    }

    @GET
    @Path("/costumes-by-params")
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed({"Admin", "ManagerDTO"})
    public Response getAllCostumesByParams(@QueryParam("age") String age, @QueryParam("size") String size) {
        if(age == null || age.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Age parameter is empty").build();
        }
        if(size == null || size.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Size parameter is empty").build();
        }
        try {
            return Response.ok().entity(getCostumeUseCase.getAllCostumesByParams(age, size)).build();
        } catch(EntityValidationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // CREATE
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //@RolesAllowed({"Admin", "ManagerDTO"})
    public Response addCostume( CostumeDTO costumeDto) {
        if(costumeDto == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Given costume is null").build();
        }
        try {
            createCostumeUsecase.addCostume(costumeMapper.mapToCostume(costumeDto));
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
//    @RolesAllowed({"Admin", "ManagerDTO"})
    public Response updateCostume( @PathParam("id") String id, CostumeDTO costumeDto){//, @NotNull @NotEmpty @HeaderParam("If-Match") String etag) throws CostumeByIdNotFound {
        if(id == null || id.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Id parameter is empty").build();
        }
        if(costumeDto == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Given costume is null").build();
        }
        try {
//            if (!SignatureVerifier.verifyEntityIntegrity(etag, costume)) {
//                throw new IllegalArgumentException("Trying to modify the wrong costume!");
//            }
            updateCostumeUseCase.updateCostume(UUID.fromString(id), costumeMapper.mapToCostume(costumeDto));
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
//    @RolesAllowed({"Admin", "ManagerDTO"})
//    public Response activateRent(@PathParam("id") String id) {
//        if(id == null || id.trim().equals("")) {
//            return Response.status(Response.Status.BAD_REQUEST).entity("Id parameter is empty").build();
//        }
//        try {
//            UpdateCostumeUseCase.activateRent(UUID.fromString(id));
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
//    @RolesAllowed({"Admin", "ManagerDTO"})
//    public Response deactivateRent(@PathParam("id") String id) {
//        if(id == null || id.trim().equals("")) {
//            return Response.status(Response.Status.BAD_REQUEST).entity("Id parameter is empty").build();
//        }
//        try {
//            UpdateCostumeUseCase.deactivateRent(UUID.fromString(id));
//            return Response.ok(Response.Status.OK)
//                    .entity("Rent activated")
//                    .build();
//        } catch(CostumeByIdNotFound e) {
//            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
//        }
//    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed({"Admin", "ManagerDTO"})
    public Response removeCostume(@PathParam("id") String id) {
        if(id == null || id.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Id parameter is empty").build();
        }
        try {
            removeCostumeUseCase.removeCostume(UUID.fromString(id));
            return Response.ok(Response.Status.OK)
                    .entity("Rent removed")
                    .build();
        } catch(CostumeByIdNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
//        } catch(CostumeInUseException e) {
//            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        }
    }




}
