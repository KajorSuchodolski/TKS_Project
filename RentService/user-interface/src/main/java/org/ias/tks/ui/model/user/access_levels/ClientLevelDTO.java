package org.ias.tks.ui.model.user.access_levels;

import java.io.Serializable;

public class ClientLevelDTO extends AccessLevelDTO implements Serializable {

    public ClientLevelDTO( AccessLevelType accessLevelType ) {
        super(accessLevelType);
    }

    @Override
    public String getAccessLevelType() {
        return "Client";
    }

    @Override
    public void setAccessLevelType( AccessLevelType accessLevelType ) {
        super.setAccessLevelType(accessLevelType);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

