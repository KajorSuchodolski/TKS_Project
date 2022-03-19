package com.pas.rest_pas.entities.user.access_levels;

import javax.annotation.security.DeclareRoles;
import java.io.Serializable;

@DeclareRoles("Admin")
public class Administrator extends AccessLevel implements Serializable {

    public Administrator( AccessLevelType accessLevelType ) {
        super(accessLevelType);
    }

    @Override
    public String getAccessLevelType() {
        return "Admin";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
