package org.ias.tks.restadapters.dto.user.access_levels;

import java.io.Serializable;

public class AdministratorDTO extends AccessLevelDTO implements Serializable {

    public AdministratorDTO(AccessLevelTypeDTO accessLevelType) {
        super(accessLevelType);
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
