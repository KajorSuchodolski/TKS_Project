package org.ias.tks.ui.beans.user;

import org.ias.tks.ui.model.user.UserDTO;
import org.ias.tks.ui.rest_clients.UserRestClient;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class UserCreateBean implements Serializable {

    @Inject
    private UserRestClient userRestClient;

    private UserDTO createdUser = new UserDTO();

    public String createUser() {
        userRestClient.postUser(createdUser);
        return "userList";
    }

    public UserDTO getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser( UserDTO createdUser ) {
        this.createdUser = createdUser;
    }

    public UserRestClient getUserRestClient() {
        return userRestClient;
    }

    public void setUserRestClient( UserRestClient userRestClient ) {
        this.userRestClient = userRestClient;
    }
}
