package org.ias.tks.restadapters.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ias.tks.restadapters.dto.ModelDTO;


@Getter
@Setter
@NoArgsConstructor
public class UserInputDto extends ModelDTO {


    private boolean isActive = true;

    private String accessLevel;

    private String firstName;


    private String lastName;


    private String login;

    private String email;

    private String password;

    public UserInputDto(String firstName, String lastName, String login, String password, String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;

    }

}
