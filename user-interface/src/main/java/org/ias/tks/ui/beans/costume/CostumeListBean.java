package org.ias.tks.ui.beans.costume;

import org.ias.tks.ui.model.RentDTO;
import org.ias.tks.ui.model.costume.CostumeDTO;
import org.ias.tks.ui.rest_clients.CostumeRestClient;
import org.ias.tks.ui.rest_clients.RentRestClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class CostumeListBean implements Serializable {

    private CostumeRestClient costumeRestClient;
    private RentRestClient rentRestClient;

    private List<CostumeDTO> allCostumes;
    private List<CostumeDTO> foundByName;
    private List<RentDTO> rentsList = new ArrayList<>();

    private CostumeDTO costumeUpdate = new CostumeDTO();
    private CostumeDTO gotById = new CostumeDTO();
    private CostumeDTO costumeDTO = new CostumeDTO();


    private String idDeletion;
    private String idUpdate;
    private String costumeName;
    private String costumeDetailsId;

    private String searchName;
    private String getId;
    private String costumeToDelete;

    @Inject
    private CostumeUpdateBean costumeUpdateBean;
    @Inject
    private CostumeDetailsBean costumeDetailsBean;

    @Inject
    public CostumeListBean( CostumeRestClient costumeRestClient, RentRestClient rentRestClient ) {
        this.costumeRestClient = costumeRestClient;
        this.rentRestClient = rentRestClient;

    }

    public CostumeListBean() {

    }



    private CostumeDTO newCostume = new CostumeDTO();

    public CostumeDTO getNewCostume() {
        return newCostume;
    }

    public void setNewCostume(CostumeDTO newCostume) {
        this.newCostume = newCostume;
    }

    public String getIdDeletion() {
        return idDeletion;
    }

    public void setIdDeletion(String idDeletion) {
        this.idDeletion = idDeletion;
    }

    public String getIdUpdate() {
        return idUpdate;
    }

    public void setIdUpdate(String idUpdate) {
        this.idUpdate = idUpdate;
    }

    public CostumeDTO getCostumeUpdate() {
        return costumeUpdate;
    }

    public void setCostumeUpdate(CostumeDTO costumeUpdate) {
        this.costumeUpdate = costumeUpdate;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getGetId() {
        return getId;
    }

    public void setGetId(String getId) {
        this.getId = getId;
    }

    public CostumeDTO getGotById() {
        return gotById;
    }

    public void setGotById(CostumeDTO gotById) {
        this.gotById = gotById;
    }

    public List<CostumeDTO> getFoundByName() {
        return foundByName;
    }

    public void setFoundByName(List<CostumeDTO> foundByName) {
        this.foundByName = foundByName;
    }

    public CostumeUpdateBean getCostumeUpdateBean() {
        return costumeUpdateBean;
    }

    public void setCostumeUpdateBean( CostumeUpdateBean costumeUpdateBean ) {
        this.costumeUpdateBean = costumeUpdateBean;

    }

    public String getCostumeToDelete() {
        return costumeToDelete;
    }

    public void setCostumeToDelete( String costumeToDelete ) {
        this.costumeToDelete = costumeToDelete;
    }

    // CREATE
    public void createCostume() {
        costumeRestClient.postCostume(newCostume);
    }

    // DELETE

    public void beforeDeleteCostume() {


//
    }

    public void deleteCostume() {

        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        costumeToDelete = params.get("deleted");
        costumeRestClient.deleteCostume(costumeToDelete);
        allCostumes = costumeRestClient.getAllCostumes();
    }

    // UPDATE

    public void updateCostume() {
        costumeRestClient.updateCostume(costumeUpdate);
    }

    // READ - W KONSTRUKTORZE

    // READ - SEARCH BY NAME

    public void searchCostumeByName() {
        foundByName = costumeRestClient.searchCostumeByName(searchName);
    }

    // READ - GET BY ID

    public void getCostumeById() {
        gotById = costumeRestClient.getCostumeById(getId);
    }

    @PostConstruct
    public void init() {
        if(searchName == "" || searchName == " " || searchName == null) {
            allCostumes = costumeRestClient.getAllCostumes();
        } else {
            allCostumes = costumeRestClient.getAllCostumes();
        }
    }

    public List<CostumeDTO> getAllCostumes() {
        return allCostumes;
    }

    public String getAllRents(String id) {

        costumeDetailsBean.getAllRents(id);
        return "costumeDetails";
    }

    public String goBack() {
        return "goBack";
    }

    public String goUpdate(String id) {
        costumeUpdateBean.setUpdatedCostume(costumeRestClient.getCostumeById(id));
        costumeUpdateBean.setId(id);
        return "goUpdate";
    }

    public List<RentDTO> getRentsList() {
        return rentsList;
    }

    public void setRentsList( List<RentDTO> rentsList ) {
        this.rentsList = rentsList;
    }
}
