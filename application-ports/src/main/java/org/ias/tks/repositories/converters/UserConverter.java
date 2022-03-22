package org.ias.tks.repositories.converters;


import org.ias.tks.entities.user.UserEntity;
import org.ias.tks.entities.user.access_levels.*;
import org.ias.tks.model.user.access_levels.AccessLevelType;
import org.ias.tks.model.user.User;
import org.ias.tks.model.user.access_levels.Manager;

public class UserConverter {

    // TODO
    // FIX ACCESS LEVEL ADDITION
    public UserEntity convert(User user) {

        // TODO
        AccessLevelEntity accessLevel = switch(user.getAccessLevel()) {
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
                accessLevel
        );
    }

}
