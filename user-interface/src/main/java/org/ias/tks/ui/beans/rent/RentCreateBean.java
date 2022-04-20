package org.ias.tks.ui.beans.rent;

import org.ias.tks.ui.model.costume.CostumeDTO;
import org.ias.tks.ui.model.user.UserDTO;
import org.ias.tks.ui.rest_clients.CostumeRestClient;
import org.ias.tks.ui.rest_clients.RentRestClient;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@SessionScoped
@Named
public class RentCreateBean implements Serializable {

    @Inject
    private RentRestClient rentRestClient;
    @Inject
    private CostumeRestClient costumeRestClient;
    @Inject
    private UserRestClient userRestClient;

    private List<UserDTO> userList;
    private List<CostumeDTO> allAvailableCostumes;
    private String loginCreation;
    private String dateCreation = "";

    public void createRent() {
        List<UUID> costumesCreation = new ArrayList<>();
        for (CostumeDTO costume : allAvailableCostumes) {
            if (costume.isSelected()) {
                costumesCreation.add(costume.getId());
                costume.setSelected(false);
            }
        }
        if( Objects.equals(dateCreation, "")) {
            rentRestClient.createRent(loginCreation, costumesCreation, "now");
        } else {
            rentRestClient.createRent(loginCreation, costumesCreation, dateCreation);
        }
    }

    public void validateDate( FacesContext context, UIComponent component, Object value) {
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

    public List<CostumeDTO> getAllAvailableCostumes() {

        allAvailableCostumes = costumeRestClient.getAllAvailableCostumes();
        return allAvailableCostumes;
    }

    public void setAllAvailableCostumes( List<CostumeDTO> allAvailableCostumes ) {
        this.allAvailableCostumes = allAvailableCostumes;
    }

    public RentRestClient getRentRestClient() {
        return rentRestClient;
    }

    public void setRentRestClient( RentRestClient rentRestClient ) {
        this.rentRestClient = rentRestClient;
    }

    public UserRestClient getUserRestClient() {
        return userRestClient;
    }

    public void setUserRestClient( UserRestClient userRestClient ) {
        this.userRestClient = userRestClient;
    }

    public String getLoginCreation() {
        return loginCreation;
    }

    public void setLoginCreation( String loginCreation ) {
        this.loginCreation = loginCreation;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation( String dateCreation ) {
        this.dateCreation = dateCreation;
    }

    public List<UserDTO> getUserList() {
        userList = userRestClient.getAllUsers();
        return userList;
    }

    public void setUserList( List<UserDTO> userList ) {
        this.userList = userList;
    }

    public CostumeRestClient getCostumeRestClient() {
        return costumeRestClient;
    }

    public void setCostumeRestClient( CostumeRestClient costumeRestClient ) {
        this.costumeRestClient = costumeRestClient;
    }
}
