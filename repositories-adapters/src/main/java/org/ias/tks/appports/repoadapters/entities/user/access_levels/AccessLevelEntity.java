package org.ias.tks.appports.repoadapters.entities.user.access_levels;

import java.io.Serializable;

public abstract class AccessLevelEntity implements Serializable {

    private AccessLevelTypeEntity accessLevelTypeEnt;

    public AccessLevelEntity(AccessLevelTypeEntity accessLevelTypeEnt) {
        this.accessLevelTypeEnt = accessLevelTypeEnt;
    }

    public abstract String getAccessLevelType();

    public void setAccessLevelType(AccessLevelTypeEntity accessLevelTypeEnt) {
        this.accessLevelTypeEnt = accessLevelTypeEnt;
    }

    @Override
    public String toString() {
        return "AccessLevel: " + getAccessLevelType();
    }
}
