package org.ias.tks.repoadapters.entities.user.access_levels;

import javax.annotation.security.DeclareRoles;
import java.io.Serializable;

@DeclareRoles("Manager")
public class ManagerEntity extends AccessLevelEntity implements Serializable {

    public ManagerEntity(AccessLevelTypeEntity accessLevelTypeEnt) {
        super(accessLevelTypeEnt);
    }

    @Override
    public String getAccessLevelType() {
        return "Manager";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
