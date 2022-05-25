package org.ias.tks.ui.beans.user;

import org.ias.tks.ui.model.RentDTO;
import org.ias.tks.ui.rest_clients.RentRestClient;
import org.ias.tks.ui.rest_clients.UserRestClient;
import org.ias.tks.ui.model.user.UserDTO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class UserListBean implements Serializable  {

    @Inject
    private UserRestClient userRestClient;
    @Inject
    private RentRestClient rentRestClient;
    @Inject
    private UserUpdateBean userUpdateBean;


    private UserDTO newUser = new UserDTO();
    private UserDTO foundLoginUser = new UserDTO();
    private UserDTO foundIdUser = new UserDTO();

    private List<RentDTO> userRents = new ArrayList<>();

    private List<UserDTO> allUsers = new ArrayList<>();
    private List<UserDTO> foundUsers = new ArrayList<>();

    private String searchLogin;
    private String getLogin;
    private String getId;
    private String updateLogin;

    private String userDetailsLogin;

    public void setAllUsers(List<UserDTO> allUsers) {
        this.allUsers = allUsers;
    }

    public List<UserDTO> getFoundUsers() {
        return foundUsers;
    }

    public void setFoundUsers(List<UserDTO> foundUsers) {
        this.foundUsers = foundUsers;
    }


    public UserDTO getFoundLoginUser() {
        return foundLoginUser;
    }

    public void setFoundLoginUser(UserDTO foundLoginUser) {
        this.foundLoginUser = foundLoginUser;
    }

    public String getSearchLogin() {
        return searchLogin;
    }

    public void setSearchLogin(String searchLogin) {
        this.searchLogin = searchLogin;
    }

    public String getGetId() {
        return getId;
    }

    public void setGetId(String getId) {
        this.getId = getId;
    }

    public String getGetLogin() {
        return getLogin;
    }

    public void setGetLogin(String getLogin) {
        this.getLogin = getLogin;
    }

    public UserDTO getFoundIdUser() {
        return foundIdUser;
    }

    public void setFoundIdUser(UserDTO foundIdUser) {
        this.foundIdUser = foundIdUser;
    }

    public UserUpdateBean getUserUpdateBean() {
        return userUpdateBean;
    }

    public void setUserUpdateBean( UserUpdateBean userUpdateBean ) {
        this.userUpdateBean = userUpdateBean;
    }

    public UserRestClient getUserRestClient() {
        return userRestClient;
    }

    public void setUserRestClient( UserRestClient userRestClient ) {
        this.userRestClient = userRestClient;
    }

    public RentRestClient getRentRestClient() {
        return rentRestClient;
    }

    public void setRentRestClient( RentRestClient rentRestClient ) {
        this.rentRestClient = rentRestClient;
    }

    // CREATE

    public void createUser() {
        userRestClient.postUser(newUser);
    }

    // READ - W KONSTRUKTORZE

    @PostConstruct
    public void init() {
        if(getLogin == "" || getLogin == " " || getLogin == null) {
            allUsers = userRestClient.getAllUsers();
        } else {
            allUsers = userRestClient.getAllUsers();
        }
    }
    public List<UserDTO> getAllUsers() {
        return allUsers;
    }


    // READ - SEARCH BY LOGIN (LIST)

    public List<UserDTO> searchByLogin() {
        return userRestClient.searchUserByLogin(getLogin);
    }

    // READ - GET BY LOGIN (USER)

    public void getByLogin() {
        foundLoginUser = userRestClient.getUserByLogin(getLogin);
    }

    // READ - GET BY ID (USER)

    public void getById() {
        foundIdUser = userRestClient.getUserById(getId);
    }

    // UPDATE - ACTIVATE

    public String activateUser(String login) {

        userRestClient.activateUser(login);
        init();
        return "userList";
    }

    // UPDATE - DEACTIVATE

    public String deactivateUser(String login) {
        userRestClient.deactivateUser(login);
        init();
        return "userList";

    }

    public String getUpdateLogin() {
        return updateLogin;
    }

    public void setUpdateLogin( String updateLogin ) {
        this.updateLogin = updateLogin;
    }


    public String goBack() {

        if(!userRents.isEmpty()) {
            userRents.clear();
        }
        return "goBack";
    }

    public List<RentDTO> getUserRents() {
        return userRents;
    }

    public void setUserRents( List<RentDTO> userRents ) {
        this.userRents = userRents;
    }

    public String goUpdate(String login) {

        userUpdateBean.setUpdatedUser(userRestClient.getUserByLogin(login));
        userUpdateBean.setUserLogin(login);
        return "updateUser";
    }

    public String getUserDetailsLogin() {
        return userDetailsLogin;
    }

    public void setUserDetailsLogin( String userDetailsLogin ) {
        this.userDetailsLogin = userDetailsLogin;
    }
}