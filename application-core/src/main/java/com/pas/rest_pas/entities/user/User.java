package com.pas.rest_pas.entities.user;

import com.nimbusds.jose.shaded.json.annotate.JsonIgnore;
import com.pas.rest_pas.entities.Entity;
import com.pas.rest_pas.entities.Singable;
import com.pas.rest_pas.entities.user.access_levels.AccessLevel;

import javax.json.bind.annotation.*;
import java.io.Serializable;
import java.util.Objects;

@JsonbNillable(value = true)
public class User extends Entity implements Singable, Serializable {


    @JsonbProperty
    private boolean isActive = true;

    @JsonbProperty
    private AccessLevel accessLevel;

    @JsonbProperty
    private String firstName;

    @JsonbProperty
    private String lastName;

    @JsonbProperty
    private String login;

    @JsonbProperty
    private String email;

    @JsonIgnore
    private String password;

    public User() {

    }

    @JsonbCreator
    public User(@JsonbProperty("firstName") String firstName, @JsonbProperty("lastName") String lastName, @JsonbProperty("login") String login, @JsonbProperty("password") String password, @JsonbProperty("email") String email, @JsonbProperty("accessLevel") AccessLevel accessLevel) {

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
        return accessLevel.getAccessLevelType();
    }

    public void setAccessLevel( AccessLevel accessLevel ) {
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

    public void setLogin(String login) {
        this.login = login;
    }


    @JsonbTransient
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


    @Override
    public boolean equals( Object o ) {
        if( this == o ) return true;
        if( !(o instanceof User user) ) return false;
        if( !super.equals(o) ) return false;
        return isActive == user.isActive && Objects.equals(accessLevel, user.accessLevel) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(login, user.login) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isActive, accessLevel, firstName, lastName, login, email, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "isActive=" + isActive +
                ", accessLevel=" + accessLevel +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @JsonbTransient
    @Override
    public String getSingablePayload() {
        return login + getId().toString();
    }
}
