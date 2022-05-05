package org.ias.tks.soapadapters.mappers;

import org.ias.tks.appcore.domainmodel.model.user.User;
import org.ias.tks.appcore.domainmodel.model.user.access_levels.*;
import org.ias.tks.soapadapters.dto.user.UserInputSOAP;
import org.ias.tks.soapadapters.dto.user.UserOutputSOAP;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UserMapper {

    public UserOutputSOAP mapToUserOutputSOAP(User user) {

        // TODO
        UserOutputSOAP userOutputDto = new UserOutputSOAP(
                user.getFirstName(),
                user.getLastName(),
                user.getLogin(),
                user.getEmail(),
                user.isActive());

        userOutputDto.setAccessLevel(user.getAccessLevel());
        userOutputDto.setId(user.getId().toString());
        return userOutputDto;
    }

    public UserInputSOAP mapToUserInputSOAP(User user) {

        // TODO
        UserInputSOAP userInputSOAP = new UserInputSOAP(
                user.getFirstName(),
                user.getLastName(),
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.getAccessLevel());

        userInputSOAP.setId(user.getId().toString());
        return userInputSOAP;
    }


    public User mapToUser(UserInputSOAP userInputSOAP) {

        AccessLevel accessLevel;
        switch (userInputSOAP.getAccessLevel()) {
            case "Admin":
                accessLevel = new Administrator(AccessLevelType.ADMINISTRATOR);
                break;
            case "Manager":
                accessLevel = new Manager(AccessLevelType.MANAGER);
                break;
            default:
                accessLevel = new Client(AccessLevelType.CLIENT);
                break;
        };

        User user = new User(
                userInputSOAP.getFirstName(),
                userInputSOAP.getLastName(),
                userInputSOAP.getLogin(),
                userInputSOAP.getPassword(),
                userInputSOAP.getEmail(),
                accessLevel);

        user.setId(UUID.fromString(userInputSOAP.getId()));
        return user;
    }



    public List<UserOutputSOAP> mapToUserSOAPList(List<User> list) {
        List<UserOutputSOAP> userOutputDtos = new ArrayList<>();
        for (User user : list) {
            userOutputDtos.add(mapToUserOutputSOAP(user));
        }
        return userOutputDtos;
    }
}
