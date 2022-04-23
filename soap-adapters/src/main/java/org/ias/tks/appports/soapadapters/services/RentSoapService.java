package org.ias.tks.appports.soapadapters.services;

import org.ias.tks.appcore.domainmodel.exceptions.*;
import org.ias.tks.appports.soapadapters.adapters.RentSOAPAdapter;
import org.ias.tks.appports.soapadapters.dto.rent.RentOutputSOAP;

import javax.inject.Inject;
import javax.jws.WebService;
import java.util.List;


@WebService(serviceName = "rentsoapapi")
public class RentSoapService {
    @Inject
    private RentSOAPAdapter rentSOAPAdapter;

    //    public Response addRent(@QueryParam("login") String login, @QueryParam("date") String date, List<UUID> costumeIds) {
//        if (login == null || login.trim().equals("")) {
//            return Response.status(Response.Status.BAD_REQUEST).entity("Login parameter is empty").build();
//        }
//        if (date == null || date.trim().equals("")) {
//            return Response.status(Response.Status.BAD_REQUEST).entity("Date parameter is empty").build();
//        }
//        if (costumeIds == null || costumeIds.isEmpty()) {
//            return Response.status(Response.Status.BAD_REQUEST).entity("Given list is empty").build();
//        }
//        try {
//            rentRestAdapter.addRent(login, costumeIds, date);
//            return Response.ok(Response.Status.CREATED)
//                    .entity("Rent added successfully")
//                    .build();
//        } catch (CostumeInUseException e) {
//            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
//        } catch (CostumeByIdNotFound | UserByLoginNotFound e) {
//            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
//        } catch (DateInPastException | WrongDateFormatException e) {
//            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
//        }
//    }
//
//    // READ
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
////    @RolesAllowed({"Admin", "ClientDTO"})

    public List<RentOutputSOAP> getAll() {
        return rentSOAPAdapter.getAll();
    }

//
//
//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
////    @RolesAllowed({"Admin", "ClientDTO"})
//    public Response getRentById(@PathParam("id") String rentId) {
//        if (rentId == null || rentId.trim().equals("")) {
//            return Response.status(Response.Status.BAD_REQUEST).entity("RentID parameter is empty").build();
//        }
//        try {
//            return Response.ok()
//                    .entity(rentRestAdapter.getRentById(UUID.fromString(rentId)))
//                    .build();
//        } catch (RentByIdNotFound e) {
//            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
//        }
//    }
//
//    @GET
//    @Path("/{login}/current-rents")
//    @Produces(MediaType.APPLICATION_JSON)
////    @RolesAllowed({"Admin", "ClientDTO"})
//    public Response userCurrentRents(@PathParam("login") String login) {
//        if (login == null || login.trim().equals("")) {
//            return Response.status(Response.Status.BAD_REQUEST).entity("Login parameter is empty").build();
//        }
//        try {
//            return Response.ok().entity(rentRestAdapter.userCurrentRents(login)).build();
//        } catch (UserByLoginNotFound e) {
//            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
//        }
//
//    }
//
//    @GET
//    @Path("/{login}/past-rents")
//    @Produces(MediaType.APPLICATION_JSON)
////    @RolesAllowed({"Admin", "ClientDTO"})
//    public Response userPastRents(@PathParam("login") String login) {
//        if (login == null || login.trim().equals("")) {
//            return Response.status(Response.Status.BAD_REQUEST).entity("Login parameter is empty").build();
//        }
//        try {
//            return Response.ok().entity(rentRestAdapter.userPastRents(login)).build();
//        } catch (UserByLoginNotFound e) {
//            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
//        }
//    }
//
//    @GET
//    @Path("/costume-allocations")
//    @Produces(MediaType.APPLICATION_JSON)
////    @RolesAllowed({"Admin", "ManagerDTO", "ClientDTO"})
//    public Response getCostumeAllocations(@QueryParam("id") String costumeId) {
//        if (costumeId == null || costumeId.trim().equals("")) {
//            return Response.status(Response.Status.BAD_REQUEST).entity("RentID parameter is empty").build();
//        }
//        try {
//            return Response.ok(Response.Status.OK)
//                    .entity(rentRestAdapter.getCostumeAllocations(UUID.fromString(costumeId)))
//                    .build();
//        } catch (CostumeByIdNotFound e) {
//            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
//        }
//    }
//
//    @PUT
//    @Path("/{id}/end")
//    @Produces(MediaType.APPLICATION_JSON)
////    @RolesAllowed({"Admin", "ClientDTO"})
//    public Response endRent(@PathParam("id") String rentId, @QueryParam("date") String date) throws RentByIdNotFound {
//        if (rentId == null || rentId.trim().equals("")) {
//            return Response.status(Response.Status.BAD_REQUEST).entity("RentID parameter is empty").build();
//        }
//        if (date == null || date.trim().equals("")) {
//            return Response.status(Response.Status.BAD_REQUEST).entity("Date parameter is empty").build();
//        }
//        try {
//            rentRestAdapter.endRent(date, UUID.fromString(rentId));
//            return Response.ok(Response.Status.OK)
//                    .entity("Rent was ended successfully")
//                    .build();
//        } catch (RentByIdNotFound e) {
//            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
//        } catch (EndRentBeforeBeginException e) {
//            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
//        }
//    }

}
