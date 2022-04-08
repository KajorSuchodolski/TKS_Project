package org.ias.tks.restadapters.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ias.tks.restadapters.dto.ModelDTO;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserOutputDto extends ModelDTO {


    private UUID id;

    private boolean isActive;

    private String accessLevel;

    private String firstName;

    private String lastName;

    private String login;

    private String email;


    public UserOutputDto(String firstName, String lastName, String login, String email, boolean isActive) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.email = email;
        this.isActive = isActive;

    }
}
