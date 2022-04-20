package org.ias.tks.ui.model.user.access_levels;

import java.io.Serializable;

public abstract class AccessLevelDTO implements Serializable {
    
    private AccessLevelType accessLevelType;

    public AccessLevelDTO( AccessLevelType accessLevelType) {
        this.accessLevelType = accessLevelType;
    }

    public abstract String getAccessLevelType();

    public void setAccessLevelType( AccessLevelType accessLevelType ) {
        this.accessLevelType = accessLevelType;
    }

    @Override
    public String toString() {
        return "AccessLevelDTO{}";
    }
}
