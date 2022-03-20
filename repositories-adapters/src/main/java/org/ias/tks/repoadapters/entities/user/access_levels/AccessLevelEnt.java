package org.ias.tks.repoadapters.entities.user.access_levels;

import java.io.Serializable;

public abstract class AccessLevelEnt implements Serializable {

    private AccessLevelTypeEnt accessLevelTypeEnt;

    public AccessLevelEnt(AccessLevelTypeEnt accessLevelTypeEnt) {
        this.accessLevelTypeEnt = accessLevelTypeEnt;
    }

    public abstract String getAccessLevelType();

    public void setAccessLevelType(AccessLevelTypeEnt accessLevelTypeEnt) {
        this.accessLevelTypeEnt = accessLevelTypeEnt;
    }

    @Override
    public String toString() {
        return "AccessLevel: " + getAccessLevelType();
    }
}
