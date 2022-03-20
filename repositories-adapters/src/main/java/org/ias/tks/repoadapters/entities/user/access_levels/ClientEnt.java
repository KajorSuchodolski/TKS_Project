package org.ias.tks.repoadapters.entities.user.access_levels;

import javax.annotation.security.DeclareRoles;
import java.io.Serializable;

@DeclareRoles("Client")
public class ClientEnt extends AccessLevelEnt implements Serializable {

    public ClientEnt(AccessLevelTypeEnt accessLevelTypeEnt) {
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

