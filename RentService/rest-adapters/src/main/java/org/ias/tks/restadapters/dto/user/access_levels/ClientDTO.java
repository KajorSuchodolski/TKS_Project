package org.ias.tks.restadapters.dto.user.access_levels;

import java.io.Serializable;

public class ClientDTO extends AccessLevelDTO implements Serializable {

    public ClientDTO(AccessLevelTypeDTO accessLevelType ) {
        super(accessLevelType);
    }

    @Override
    public String getAccessLevelType() {
        return "ClientDTO";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

