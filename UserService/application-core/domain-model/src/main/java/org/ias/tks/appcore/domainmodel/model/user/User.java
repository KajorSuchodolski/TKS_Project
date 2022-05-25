package org.ias.tks.appcore.domainmodel.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.ias.tks.appcore.domainmodel.model.Model;
import org.ias.tks.appcore.domainmodel.model.user.access_levels.AccessLevel;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
public class User extends Model implements Serializable {

    private boolean isActive = true;

    private AccessLevel accessLevel;

    private String firstName;

    private String lastName;

    private String login;

    private String email;

    private String password;

    public String getAccessLevel() {
        return accessLevel.getAccessLevelType();
    }

    public User(String firstName, String lastName, String login, String password, String email, AccessLevel accessLevel) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.accessLevel = accessLevel;

    }
}
