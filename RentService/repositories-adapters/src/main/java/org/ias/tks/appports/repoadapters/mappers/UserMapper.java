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
        AccessLevelEntity accessLevel;
        switch (user.getAccessLevel()) {
            case "Admin":
                accessLevel = new AdministratorEntity(AccessLevelTypeEntity.ADMINISTRATOR);
                break;
            case "Manager":
                accessLevel = new ManagerEntity(AccessLevelTypeEntity.MANAGER);
                break;
            default:
                accessLevel = new ClientEntity(AccessLevelTypeEntity.CLIENT);
                break;
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

        AccessLevel accessLevel;
        switch (userEntity.getAccessLevel()) {
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
