package org.ias.tks.Mappers;


import org.ias.tks.entities.user.UserEntity;
import org.ias.tks.entities.user.access_levels.*;

import org.ias.tks.model.user.User;
import org.ias.tks.model.user.access_levels.*;

import java.util.ArrayList;
import java.util.List;


public class UserMapper {


    // TODO
    // FIX ACCESS LEVEL ADDITION
    public UserEntity mapToUserEntity(User user) {

        // TODO
        AccessLevelEntity accessLevel = switch (user.getAccessLevel()) {
            case "Admin" -> new AdministratorEntity(AccessLevelTypeEntity.ADMINISTRATOR);
            case "Manager" -> new ManagerEntity(AccessLevelTypeEntity.MANAGER);
            case "Client" -> new ClientEntity(AccessLevelTypeEntity.CLIENT);
        };

        return new UserEntity(
                user.getFirstName(),
                user.getLastName(),
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                accessLevel);
    }

    public User mapToUser(UserEntity userEntity) {

        AccessLevel accessLevel = switch (userEntity.getAccessLevel()) {
            case "Admin" -> new Administrator(AccessLevelType.ADMINISTRATOR);
            case "Manager" -> new Manager(AccessLevelType.MANAGER);
            case "Client" -> new Client(AccessLevelType.CLIENT);
        };

        return new User(
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getLogin(),
                userEntity.getPassword(),
                userEntity.getEmail(),
                accessLevel);
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
