package org.ias.tks.appports.repoadapters.entities.user;

import org.ias.tks.appports.repoadapters.entities.user.access_levels.AccessLevelEntity;
import org.ias.tks.appports.repoadapters.entities.Entity;
import org.ias.tks.appports.repoadapters.entities.SingableEntity;

import java.io.Serializable;
import java.util.Objects;


public class UserEntity extends Entity implements SingableEntity, Serializable {


    private boolean isActive = true;
    private AccessLevelEntity accessLevel;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String password;

    public UserEntity() {

    }

    public UserEntity(String firstName, String lastName, String login, String password, String email, AccessLevelEntity accessLevel) {

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

    public void setAccessLevel(AccessLevelEntity accessLevel) {
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
        if (!(o instanceof UserEntity userEntity)) return false;
        if (!super.equals(o)) return false;
        return isActive == userEntity.isActive && Objects.equals(accessLevel, userEntity.accessLevel) && Objects.equals(firstName, userEntity.firstName) && Objects.equals(lastName, userEntity.lastName) && Objects.equals(login, userEntity.login) && Objects.equals(email, userEntity.email) && Objects.equals(password, userEntity.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isActive, accessLevel, firstName, lastName, login, email, password);
    }

    @Override
    public String toString() {
        return "UserEntityEntity{" +
                "isActive=" + isActive +
                ", accessLevel=" + accessLevel +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public String getSingablePayload() {
        return login + getId().toString();
    }
}
