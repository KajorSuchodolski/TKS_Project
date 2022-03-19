package com.pas.rest_pas.entities.user.access_levels;

import java.io.Serializable;

public abstract class AccessLevel implements Serializable {
    
    private AccessLevelType accessLevelType;

    public AccessLevel(AccessLevelType accessLevelType) {
        this.accessLevelType = accessLevelType;
    }

    public abstract String getAccessLevelType();

    public void setAccessLevelType( AccessLevelType accessLevelType ) {
        this.accessLevelType = accessLevelType;
    }

    @Override
    public String toString() {
        return "AccessLevel{}";
    }
}
