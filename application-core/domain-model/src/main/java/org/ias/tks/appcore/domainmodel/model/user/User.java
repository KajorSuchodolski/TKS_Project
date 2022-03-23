package org.ias.tks.appcore.domainmodel.model.user;

import org.ias.tks.appcore.domainmodel.model.Model;
import org.ias.tks.appcore.domainmodel.model.user.access_levels.AccessLevel;

import java.io.Serializable;
import java.util.Objects;

public class User extends Model implements Serializable {

    private boolean isActive = true;

    private AccessLevel accessLevel;

    private String firstName;

    private String lastName;

    private String login;

    private String email;

    private String password;

    public User() {

    }

    public User(String firstName, String lastName, String login, String password, String email, AccessLevel accessLevel) {

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

    public void setAccessLevel(AccessLevel accessLevel) {
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
        if (!(o instanceof User user)) return false;
        if (!super.equals(o)) return false;
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

}
