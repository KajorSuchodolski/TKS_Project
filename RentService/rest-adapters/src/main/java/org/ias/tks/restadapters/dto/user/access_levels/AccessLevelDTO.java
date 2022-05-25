package org.ias.tks.restadapters.dto.user.access_levels;

import java.io.Serializable;

public abstract class AccessLevelDTO implements Serializable {

    private AccessLevelTypeDTO accessLevelType;

    public AccessLevelDTO(AccessLevelTypeDTO accessLevelType) {
        this.accessLevelType = accessLevelType;
    }

    public abstract String getAccessLevelType();

    public void setAccessLevelType(AccessLevelTypeDTO accessLevelType) {
        this.accessLevelType = accessLevelType;
    }

    @Override
    public String toString() {
        return "AccessLevelDTO: " + getAccessLevelType();
    }
}
