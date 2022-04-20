package org.ias.tks.ui.rest_clients;

import org.ias.tks.ui.model.user.UserDTO;

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
public class UserRestClient implements Serializable {


    private WebTarget getTarget() {
        Client client = ClientBuilder.newClient();
        return client.target("http://localhost:8080/web-application-1.0-SNAPSHOT/user");

    }


    // CREATE
    public void postUser(UserDTO newUser) {
        getTarget()
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(newUser));
    }

    // READ
    public List<UserDTO> getAllUsers() {
        return getTarget()
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {
                });
    }

    // READ - SEARCH BY LOGIN (LIST)

    public List<UserDTO> searchUserByLogin(String login) {
        return getTarget().path("/search-by-login")
                .queryParam("login", login)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<>() {
                });
    }


    // READ - GET BY LOGIN (USER)

    public UserDTO getUserByLogin(String login) {
        Response response = getTarget().path("get-by-login")
                .queryParam("login", login)
                .request(MediaType.APPLICATION_JSON)
                .get();

        UserDTO userDTO = getTarget().path("get-by-login")
                .queryParam("login", login)
                .request(MediaType.APPLICATION_JSON)
                .get(UserDTO.class);
        return userDTO;
    }

    // READ - GET BY ID (USER)

    public UserDTO getUserById(String id) {
        return getTarget().path("get-by-id")
                .queryParam("login", id)
                .request(MediaType.APPLICATION_JSON)
                .get(UserDTO.class);
    }

    // UPDATE

    public void updateUser(UserDTO newUser) {
        newUser.setAccessLevel("Client");
        getTarget().path(newUser.getLogin() + "/update")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(newUser));

    }

    // UPDATE - ACTIVATE

    public void activateUser(String login) {
        getTarget().path(login + "/activate")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(""));
    }

    // UPDATE - DEACTIVATE

    public void deactivateUser(String login) {
        getTarget().path(login + "/deactivate")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(""));
    }
}
