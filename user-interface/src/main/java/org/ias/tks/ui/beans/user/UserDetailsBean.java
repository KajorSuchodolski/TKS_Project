package org.ias.tks.ui.beans.user;

import org.ias.tks.ui.model.RentDTO;
import org.ias.tks.ui.rest_clients.RentRestClient;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class UserDetailsBean implements Serializable {

    private final RentRestClient rentRestClient;

    private String userDetailsLogin;
    private List<RentDTO> userRents;

    @Inject
    public UserDetailsBean( RentRestClient rentRestClient ) {
        this.rentRestClient = rentRestClient;
    }

    public String getUserDetailsLogin() {
        return userDetailsLogin;
    }

    public void setUserDetailsLogin( String userDetailsLogin ) {
        this.userDetailsLogin = userDetailsLogin;
    }

    public List<RentDTO> getUserRents() {
        return userRents;
    }

    public void setUserRents( List<RentDTO> userRents ) {
        this.userRents = userRents;
    }

    public String getAllRents( String login) {

        userDetailsLogin = login;
        List<RentDTO> currentRents = rentRestClient.userCurrentRents(login);
        List<RentDTO> pastRents = rentRestClient.userPastRents(login);

        userRents = new ArrayList<>();
        userRents.addAll(currentRents);
        userRents.addAll(pastRents);
        System.out.println(userRents);
        return "userDetails";

    }

    public String goBack() {
        return "goBack";
    }



}
