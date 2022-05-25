package org.ias.tks.appports.repoadapters.entities.user.access_levels;

import java.io.Serializable;

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
