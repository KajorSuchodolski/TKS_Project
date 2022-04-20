package org.ias.tks.ui.beans.rent;


import org.ias.tks.ui.model.user.UserDTO;
import org.ias.tks.ui.rest_clients.RentRestClient;
import org.ias.tks.ui.model.RentDTO;
import org.ias.tks.ui.rest_clients.UserRestClient;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

@SessionScoped
@Named
public class RentListBean implements Serializable {


    private RentRestClient rentRestClient;

    private RentDTO readRent = new RentDTO();

    private String userLogin;

    private String endRentId;
    private String dateEnd = "";


    private List<RentDTO> currentRentsList;
    private List<RentDTO> pastRentsList;

    private List<UserDTO> userList;

    @Inject
    public RentListBean( RentRestClient rentRestClient, UserRestClient userRestClient) {
        this.rentRestClient = rentRestClient;
        this.userList = userRestClient.getAllUsers();
        this.currentRentsList = rentRestClient.getCurrentRents();
    }



    // READ
    public List<RentDTO> getAllRents() {
        return rentRestClient.getAllRents();
    }

    public void getRentById() {
        readRent =  rentRestClient.getRentById(readRent.getId().toString());
    }

    public void userCurrentRents() {
        currentRentsList = rentRestClient.userCurrentRents(userLogin);
    }

    public void userPastRents() {
        pastRentsList = rentRestClient.userPastRents(userLogin);
    }

    public RentDTO getReadRent() {
        return readRent;
    }

    public void setReadRent( RentDTO readRent ) {
        this.readRent = readRent;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin( String userLogin ) {
        this.userLogin = userLogin;
    }

    public List<RentDTO> getCurrentRentsList() {
        return currentRentsList;
    }

    public void setCurrentRentsList( List<RentDTO> currentRentsList ) {
        this.currentRentsList = currentRentsList;
    }

    public List<RentDTO> getPastRentsList() {
        return pastRentsList;
    }

    public void setPastRentsList( List<RentDTO> pastRentsList ) {
        this.pastRentsList = pastRentsList;
    }

    public RentRestClient getRentRestClient() {
        return rentRestClient;
    }

    public void setRentRestClient(RentRestClient rentRestClient) {
        this.rentRestClient = rentRestClient;
    }


    public List<UserDTO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDTO> userList) {
        this.userList = userList;
    }

    public String getEndRentId() {
        return endRentId;
    }

    public void setEndRentId(String endRentId) {
        this.endRentId = endRentId;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }



    // UPDATE - END

    public void endRent() {
        if(Objects.equals(dateEnd, ""))
            rentRestClient.endRent(endRentId, "now");
        else {
            rentRestClient.endRent(endRentId, dateEnd);
        }
    }

    public void validateDate(FacesContext context, UIComponent component, Object value) {
        if (!Objects.equals(value.toString(), "")) {
            try {
                LocalDate dateRented = LocalDate.parse(value.toString());
                if (dateRented.isBefore(LocalDate.now())) {
                    ((UIInput) component).setValid(false);
                    FacesMessage fmsg = new FacesMessage
                            ("Date cannot be in the past");
                    context.addMessage(component.getClientId(context), fmsg);
                }
            } catch (DateTimeParseException e) {
                ((UIInput) component).setValid(false);
                FacesMessage fmsg = new FacesMessage
                        ("Wrong date format");
                context.addMessage(component.getClientId(context), fmsg);
            }
        }
    }
}
