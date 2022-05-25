package org.ias.tks.soapadapters.dto.user;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ias.tks.soapadapters.dto.ModelDTO;


@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserInputSOAP", propOrder = {
        "firstName",
        "lastName",
        "login",
        "password",
        "email",
        "accessLevel"
})
public class UserInputSOAP extends ModelDTO {


    private boolean isActive = true;

    private String accessLevel;

    private String firstName;

    private String lastName;

    private String login;

    private String email;

    private String password;

    public UserInputSOAP(String firstName, String lastName, String login, String password, String email, String accessLevel) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.accessLevel = accessLevel;

    }


    @Override
    public String getId() {
        return super.getId();
    }

}
