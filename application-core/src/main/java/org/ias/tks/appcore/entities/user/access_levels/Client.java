package org.ias.tks.appcore.entities.user.access_levels;

import javax.annotation.security.DeclareRoles;
import java.io.Serializable;

@DeclareRoles("Client")
public class Client extends AccessLevel implements Serializable {

    public Client( AccessLevelType accessLevelType ) {
        super(accessLevelType);
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

