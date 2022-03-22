package org.ias.tks.repoadapters.entities.user.access_levels;

import javax.annotation.security.DeclareRoles;
import java.io.Serializable;

@DeclareRoles("Admin")
public class AdministratorEntity extends AccessLevelEntity implements Serializable {

    public AdministratorEntity(AccessLevelTypeEntity accessLevelTypeEnt) {
        super(accessLevelTypeEnt);
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
