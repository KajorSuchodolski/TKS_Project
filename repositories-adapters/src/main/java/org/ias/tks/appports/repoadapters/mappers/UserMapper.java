package org.ias.tks.appports.repoadapters.mappers;


import org.ias.tks.appcore.domainmodel.model.user.User;
import org.ias.tks.appcore.domainmodel.model.user.access_levels.*;
import org.ias.tks.appports.repoadapters.entities.user.UserEntity;
import org.ias.tks.appports.repoadapters.entities.user.access_levels.*;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;


@RequestScoped
public class UserMapper {

    // TODO
    // FIX ACCESS LEVEL ADDITION
    public UserEntity mapToUserEntity(User user) {

        // TODO
        AccessLevelEntity accessLevel = switch (user.getAccessLevel()) {
            case "Admin" -> new AdministratorEntity(AccessLevelTypeEntity.ADMINISTRATOR);
            case "Manager" -> new ManagerEntity(AccessLevelTypeEntity.MANAGER);
            case "Client" -> new ClientEntity(AccessLevelTypeEntity.CLIENT);
            default -> new ClientEntity(AccessLevelTypeEntity.CLIENT);
        };

        UserEntity userEntity = new UserEntity(
                user.getFirstName(),
                user.getLastName(),
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                accessLevel);

        userEntity.setId(user.getId());
        userEntity.setActive(user.isActive());
        return userEntity;
    }

    public User mapToUser(UserEntity userEntity) {

        AccessLevel accessLevel = switch (userEntity.getAccessLevel()) {
            case "Admin" -> new Administrator(AccessLevelType.ADMINISTRATOR);
            case "Manager" -> new Manager(AccessLevelType.MANAGER);
            default -> new Client(AccessLevelType.CLIENT);
        };

        User user = new User(
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getLogin(),
                userEntity.getPassword(),
                userEntity.getEmail(),
                accessLevel);

        user.setId(userEntity.getId());
        user.setActive(userEntity.isActive());
        return user;
    }

    public List<User> mapToUserList(List<UserEntity> listEntity) {
        List<User> list = new ArrayList<>();

        for (UserEntity userEntity : listEntity) {
            list.add(mapToUser(userEntity));
        }
        return list;
    }

    public List<UserEntity> mapToUserEntityList(List<User> list) {
        List<UserEntity> listEntity = new ArrayList<>();
        for (User user : list) {
            listEntity.add(mapToUserEntity(user));
        }
        return listEntity;
    }

}
