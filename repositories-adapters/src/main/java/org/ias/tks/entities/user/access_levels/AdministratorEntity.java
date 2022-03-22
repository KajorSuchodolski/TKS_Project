package org.ias.tks.entities.user.access_levels;

import java.io.Serializable;

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
