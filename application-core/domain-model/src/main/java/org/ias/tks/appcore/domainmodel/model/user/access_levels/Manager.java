package org.ias.tks.appcore.domainmodel.model.user.access_levels;

import javax.annotation.security.DeclareRoles;
import java.io.Serializable;

public class Manager extends AccessLevel implements Serializable {

    public Manager( AccessLevelType accessLevelType ) {
        super(accessLevelType);
    }

    @Override
    public String getAccessLevelType() {
        return "Manager";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
