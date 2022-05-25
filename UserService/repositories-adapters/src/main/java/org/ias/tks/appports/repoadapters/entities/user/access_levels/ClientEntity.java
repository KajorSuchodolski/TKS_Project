package org.ias.tks.appports.repoadapters.entities.user.access_levels;

import java.io.Serializable;

public class ClientEntity extends AccessLevelEntity implements Serializable {

    public ClientEntity(AccessLevelTypeEntity accessLevelTypeEnt) {
        super(accessLevelTypeEnt);
    }

    @Override
    public String getAccessLevelType() {
        return "Client";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

