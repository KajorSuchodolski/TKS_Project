package org.ias.tks.ui.beans.user;

import org.ias.tks.ui.model.user.UserDTO;
import org.ias.tks.ui.rest_clients.UserRestClient;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class UserUpdateBean implements Serializable {

    @Inject
    private UserRestClient userRestClient;

    @Inject
    private UserListBean userListBean;

    private String userLogin;

    private UserDTO updatedUser = new UserDTO();


    public String updateUser() {
        System.out.println("Wywolano user beana z update user");
        System.out.println("User wyglada tak" + updatedUser.toString());


        updatedUser.setLogin(userLogin);
        userRestClient.updateUser(updatedUser);
        updatedUser = null;
        updatedUser = new UserDTO();
        userListBean.init();

        return "goBack";

    }

    public UserRestClient getUserRestClient() {
        return userRestClient;
    }

    public void setUserRestClient( UserRestClient userRestClient ) {
        this.userRestClient = userRestClient;
    }
    public UserDTO getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser( UserDTO updatedUser ) {
        this.updatedUser = updatedUser;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin( String userLogin ) {
        this.userLogin = userLogin;
    }
}
