package org.ias.tks.restadapters.mappers;

import org.ias.tks.appcore.domainmodel.model.user.User;
import org.ias.tks.appcore.domainmodel.model.user.access_levels.*;
import org.ias.tks.restadapters.dto.user.UserInputDto;
import org.ias.tks.restadapters.dto.user.UserOutputDto;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class UserMapper {

    public UserOutputDto mapToUserDTO(User user) {

        // TODO
        UserOutputDto userOutputDto = new UserOutputDto(
                user.getFirstName(),
                user.getLastName(),
                user.getLogin(),
                user.getEmail(),
                user.isActive());

        userOutputDto.setId(user.getId());
        return userOutputDto;
    }

    public User mapToUser(UserInputDto userInputDto) {

        AccessLevel accessLevel = switch (userInputDto.getAccessLevel()) {
            case "Admin" -> new Administrator(AccessLevelType.ADMINISTRATOR);
            case "Manager" -> new Manager(AccessLevelType.MANAGER);
            default -> new Client(AccessLevelType.CLIENT);
        };

        User user = new User(
                userInputDto.getFirstName(),
                userInputDto.getLastName(),
                userInputDto.getLogin(),
                userInputDto.getPassword(),
                userInputDto.getEmail(),
                accessLevel);

        user.setId(userInputDto.getId());
        return user;
    }


    public List<UserOutputDto> mapToUserDTOList(List<User> list) {
        List<UserOutputDto> userOutputDtos = new ArrayList<>();
        for (User user : list) {
            userOutputDtos.add(mapToUserDTO(user));
        }
        return userOutputDtos;
    }
}
