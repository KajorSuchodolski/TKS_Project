package org.ias.tks.appports.soapadapters.dto.user;

import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ias.tks.appports.soapadapters.dto.ModelDTO;


import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@XmlType(name = "UserOutputSoap", propOrder = {
        "id",
        "firstName",
        "lastName",
        "login",
        "email",
        "isActive"
})
public class UserOutputSOAP {

    private String id;

    private boolean isActive;

    private String accessLevel;

    private String firstName;

    private String lastName;

    private String login;

    private String email;


    public UserOutputSOAP(String firstName, String lastName, String login, String email, boolean isActive) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.email = email;
        this.isActive = isActive;

    }
}
