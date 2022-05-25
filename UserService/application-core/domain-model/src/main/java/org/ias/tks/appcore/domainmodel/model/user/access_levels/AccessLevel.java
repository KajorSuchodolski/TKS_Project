package org.ias.tks.appcore.domainmodel.model.user.access_levels;

import lombok.Setter;

import java.io.Serializable;

@Setter
public abstract class AccessLevel implements Serializable {

    private AccessLevelType accessLevelType;

    public AccessLevel(AccessLevelType accessLevelType) {
        this.accessLevelType = accessLevelType;
    }

    public abstract String getAccessLevelType();


    @Override
    public String toString() {
        return "AccessLevel: " + getAccessLevelType();
    }
}
