package org.ias.tks.appports.repoadapters.repositories;

import org.ias.tks.appports.repoadapters.entities.user.UserEntity;
import org.ias.tks.appports.repoadapters.entities.user.access_levels.AccessLevelTypeEntity;
import org.ias.tks.appports.repoadapters.entities.user.access_levels.AdministratorEntity;
import org.ias.tks.appports.repoadapters.entities.user.access_levels.ClientEntity;
import org.ias.tks.appports.repoadapters.entities.user.access_levels.ManagerEntity;
import org.ias.tks.appports.repoadapters.exceptions.UserByLoginNotFound;
import org.ias.tks.appports.repoadapters.exceptions.UserCreationException;
import org.ias.tks.appports.repoadapters.exceptions.UserUpdateException;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserRepository extends AbstractRepository<UserEntity> {

    public UserRepository() throws UserCreationException {
        UserEntity user1 = new UserEntity(
                "Radoslaw",
                "Zyzik",
                "KajorSuchodolski",
                "admin1",
                "radek2000@onet.pl",
                new ManagerEntity(AccessLevelTypeEntity.MANAGER)
        );
        UserEntity user2 = new UserEntity(
                "Radoslaw",
                "Hyzy",
                "Radek460",
                "admin1",
                "test@upd.pl",
                new ClientEntity(AccessLevelTypeEntity.CLIENT)
        );
        UserEntity user3 = new UserEntity(
                "Radoslaw",
                "Kowalski",
                "MrRadek2000",
                "admin1",
                "293032@ea.pl",
                new AdministratorEntity(AccessLevelTypeEntity.ADMINISTRATOR)
        );

        addUser(user1);
        addUser(user2);
        addUser(user3);
    }

    public synchronized void activateUser(String login) throws UserByLoginNotFound {
        if (getUserByLogin(login) == null) {
            throw new UserByLoginNotFound();
        }
        getUserByLogin(login).setActive(true);
    }

    public synchronized void deactivateUser(String login) throws UserByLoginNotFound {
        if (getUserByLogin(login) == null) {
            throw new UserByLoginNotFound();
        }
        getUserByLogin(login).setActive(false);
    }

    public UserEntity getUserByLogin(String login) {
        return getAll()
                .stream()
                .filter(e -> login.equals(e.getLogin()))
                .findAny()
                .orElse(null);
    }

    private UserEntity getUserByEmail(String login) {
        return getAll()
                .stream()
                .filter(e -> login.equals(e.getEmail()))
                .findAny()
                .orElse(null);
    }

    public List<UserEntity> searchUsersByLogin(String login) {
        return getAll()
                .stream()
                .filter(e -> e.getLogin().toLowerCase().contains(login.toLowerCase()))
                .collect(Collectors.toList());
    }

    public UserEntity findByLoginPasswordActive(String login, String password) {
        return getAll()
                .stream()
                .filter(e -> login.equals(e.getLogin()) && password.equals(e.getPassword()) && e.isActive())
                .findAny()
                .orElse(null);
    }

    public synchronized void addUser(UserEntity user) throws UserCreationException {
        if (getUserByLogin(user.getLogin()) != null) {
            throw new UserCreationException("User login already exists");
        }
        if (getUserByEmail(user.getEmail()) != null) {
            throw new UserCreationException("User email already exists");
        }
        add(user);
    }

    public synchronized void updateUser(String login, UserEntity user) throws UserUpdateException {
        if (getUserByLogin(login) == null) {
            throw new UserUpdateException("User with given login does not exist");
        } else {
            delete(getUserByLogin(login));
            user.setLogin(login);
            add(user);
        }

    }
}
