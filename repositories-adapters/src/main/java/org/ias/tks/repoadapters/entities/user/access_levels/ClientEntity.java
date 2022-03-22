package org.ias.tks.repoadapters.entities.user.access_levels;

import javax.annotation.security.DeclareRoles;
import java.io.Serializable;

@DeclareRoles("Client")
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

