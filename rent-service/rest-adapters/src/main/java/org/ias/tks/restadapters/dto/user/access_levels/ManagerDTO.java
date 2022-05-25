package org.ias.tks.restadapters.dto.user.access_levels;

import java.io.Serializable;

public class ManagerDTO extends AccessLevelDTO implements Serializable {

    public ManagerDTO(AccessLevelTypeDTO accessLevelType ) {
        super(accessLevelType);
    }

    @Override
    public String getAccessLevelType() {
        return "ManagerDTO";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
