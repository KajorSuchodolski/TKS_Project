package org.ias.tks.ui.beans.costume;

import org.ias.tks.ui.model.costume.CostumeDTO;
import org.ias.tks.ui.rest_clients.CostumeRestClient;


import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class CostumeCreateBean implements Serializable {

    @Inject
    private CostumeRestClient costumeRestClient;

    private CostumeDTO createdCostume = new CostumeDTO();

    public String createCostume() {
        costumeRestClient.postCostume(createdCostume);
        return "costumeList";
    }

    public CostumeDTO getCreatedCostume() {
        return createdCostume;
    }

    public void setCreatedCostume( CostumeDTO createdCostume ) {
        this.createdCostume = createdCostume;
    }

    public CostumeRestClient getCostumeRestClient() {
        return costumeRestClient;
    }

    public void setCostumeRestClient( CostumeRestClient costumeRestClient ) {
        this.costumeRestClient = costumeRestClient;
    }
}
