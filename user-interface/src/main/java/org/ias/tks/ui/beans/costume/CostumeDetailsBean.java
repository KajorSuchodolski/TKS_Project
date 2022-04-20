package org.ias.tks.ui.beans.costume;

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
public class CostumeDetailsBean implements Serializable {

    @Inject
    private RentRestClient rentRestClient;

    private String id;
    private List<RentDTO> costumeRents;


    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public List<RentDTO> getCostumeRents() {
        return costumeRents;
    }

    public void setCostumeRents( List<RentDTO> costumeRents ) {
        this.costumeRents = costumeRents;
    }

    public RentRestClient getRentRestClient() {
        return rentRestClient;
    }

    public void setRentRestClient( RentRestClient rentRestClient ) {
        this.rentRestClient = rentRestClient;
    }

    public String getAllRents( String id) {

        this.id = id;
        List<RentDTO> rents = rentRestClient.costumeRents(id);

        costumeRents = new ArrayList<>();
        costumeRents.addAll(rents);
        return "costumeDetails";

    }

    public String goBack() {
        return "goBack";
    }


}
