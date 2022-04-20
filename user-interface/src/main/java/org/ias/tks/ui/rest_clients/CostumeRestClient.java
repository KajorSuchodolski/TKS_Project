package org.ias.tks.ui.rest_clients;

import org.ias.tks.ui.model.costume.CostumeDTO;

import javax.enterprise.context.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;

@SessionScoped
public class CostumeRestClient implements Serializable {


    private WebTarget getTarget() {
        Client client = ClientBuilder.newClient();
        return client.target("http://localhost:8080/web-application-1.0-SNAPSHOT/costume");
    }


    // READ
    public List<CostumeDTO> getAllCostumes() {
        return getTarget()
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {
                });
    }

    // CREATE
    public void postCostume(CostumeDTO newCostume) {
        getTarget()
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(newCostume));
    }

    // DELETE
    public void deleteCostume(String idDeletion) {
        getTarget().path(idDeletion)
                .request()
                .delete();
    }

    // UPDATE

    public void updateCostume(CostumeDTO newCostume) {
        getTarget().path(newCostume.getId().toString())
                .request(MediaType.APPLICATION_JSON)
                .header("If-Match", newCostume.getEtag())
                .put(Entity.json(newCostume));
    }

    // READ - SEARCH BY NAME

    public List<CostumeDTO> searchCostumeByName(String name) {
        return getTarget().path("search-by-name")
                .queryParam("name", name)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {
                });
    }

    // READ - GET BY ID (COSTUME)

    public CostumeDTO getCostumeById(String id) {
        Response response = getTarget().path(id)
                .request(MediaType.APPLICATION_JSON)
                .get();
        String etag = (String) response.getHeaders().getFirst("Etag");

        CostumeDTO costumeDTO = getTarget().path(id)
                .request(MediaType.APPLICATION_JSON)
                .get(CostumeDTO.class);
        costumeDTO.setEtag(etag);
        return costumeDTO;
    }

    // READ - GET ALL AVAILABLE

    public List<CostumeDTO> getAllAvailableCostumes() {
        return getTarget().path("all-available")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {
                });
    }

}
