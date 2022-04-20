package org.ias.tks.ui.model.user.access_levels;


import java.io.Serializable;

public class AdministatorLevelDTO extends AccessLevelDTO implements Serializable {

    public AdministatorLevelDTO( AccessLevelType accessLevelType ) {
        super(accessLevelType);
    }

    @Override
    public String getAccessLevelType() {
        return "Admin";
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
