package org.ias.tks.repoadapters.entities.user;

import org.ias.tks.repoadapters.entities.EntityEnt;
import org.ias.tks.repoadapters.entities.SingableEnt;
import org.ias.tks.repoadapters.entities.user.access_levels.AccessLevelEnt;

import javax.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import java.util.Objects;


public class UserEnt extends EntityEnt implements SingableEnt, Serializable {


    private boolean isActive = true;
    private AccessLevelEnt accessLevel;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String password;

    public UserEnt() {

    }

    public UserEnt(String firstName, String lastName, String login, String password, String email, AccessLevelEnt accessLevel) {

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

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getAccessLevel() {
        return accessLevel.getAccessLevelType();
    }

    public void setAccessLevel(AccessLevelEnt accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEnt userEnt)) return false;
        if (!super.equals(o)) return false;
        return isActive == userEnt.isActive && Objects.equals(accessLevel, userEnt.accessLevel) && Objects.equals(firstName, userEnt.firstName) && Objects.equals(lastName, userEnt.lastName) && Objects.equals(login, userEnt.login) && Objects.equals(email, userEnt.email) && Objects.equals(password, userEnt.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isActive, accessLevel, firstName, lastName, login, email, password);
    }

    @Override
    public String toString() {
        return "UserEnt{" +
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
