package org.ias.tks.ui.model.user;

import org.ias.tks.ui.model.EntityDTO;
//import com.pas.rest_pas.entities.user.access_levels.Client;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


public class UserDTO extends EntityDTO implements Serializable {

    private boolean isActive = true;

    private String accessLevel;

    private String firstName;

    private String lastName;

    private String login;

    private String email;

    private String password;

    private UUID id;

    private String etag;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public UserDTO() {

    }

    public UserDTO( String firstName, String lastName, String login, String password, String email, String accessLevel) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.accessLevel = accessLevel;

    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive( boolean active ) {
        isActive = active;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel( String accessLevel ) {
        this.accessLevel = accessLevel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin( String login ) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag( String etag ) {
        this.etag = etag;
    }

    @Override
    public boolean equals( Object o ) {
        if( this == o ) return true;
        if( !(o instanceof UserDTO userDTO) ) return false;
        if( !super.equals(o) ) return false;
        return isActive == userDTO.isActive && Objects.equals(accessLevel, userDTO.accessLevel) && Objects.equals(firstName, userDTO.firstName) && Objects.equals(lastName, userDTO.lastName) && Objects.equals(login, userDTO.login) && Objects.equals(email, userDTO.email) && Objects.equals(password, userDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isActive, accessLevel, firstName, lastName, login, email, password);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "isActive=" + isActive +
                ", accessLevel=" + accessLevel +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
