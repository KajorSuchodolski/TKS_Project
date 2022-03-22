package org.ias.tks.appcore.repositories;

import org.ias.tks.appcore.entities.user.User;
import org.ias.tks.appcore.entities.user.access_levels.AccessLevelType;
import org.ias.tks.appcore.entities.user.access_levels.Administrator;
import org.ias.tks.appcore.entities.user.access_levels.Client;
import org.ias.tks.appcore.entities.user.access_levels.Manager;
import org.ias.tks.appcore.exceptions.UserByLoginNotFound;
import org.ias.tks.appcore.exceptions.UserCreationException;
import org.ias.tks.appcore.exceptions.UserUpdateException;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserRepository extends AbstractRepository<User> {

    public UserRepository() throws UserCreationException {
        User user1 = new User(
                "Radoslaw",
                "Zyzik",
                "KajorSuchodolski",
                "admin1",
                "radek2000@onet.pl",
                new Manager(AccessLevelType.MANAGER)
        );
        User user2 = new User(
                "Radoslaw",
                "Hyzy",
                "Radek460",
                "admin1",
                "test@upd.pl",
                new Client(AccessLevelType.CLIENT)
        );
        User user3 = new User(
                "Radoslaw",
                "Kowalski",
                "MrRadek2000",
                "admin1",
                "293032@ea.pl",
                new Administrator(AccessLevelType.ADMINISTRATOR)
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

    public User getUserByLogin(String login) {
        return getAll()
                .stream()
                .filter(e -> login.equals(e.getLogin()))
                .findAny()
                .orElse(null);
    }

    private User getUserByEmail(String login) {
        return getAll()
                .stream()
                .filter(e -> login.equals(e.getEmail()))
                .findAny()
                .orElse(null);
    }

    public List<User> searchUsersByLogin(String login) {
        return getAll()
                .stream()
                .filter(e -> e.getLogin().toLowerCase().contains(login.toLowerCase()))
                .collect(Collectors.toList());
    }

    public User findByLoginPasswordActive(String login, String password) {
        return getAll()
                .stream()
                .filter(e -> login.equals(e.getLogin()) && password.equals(e.getPassword()) && e.isActive())
                .findAny()
                .orElse(null);
    }

    public synchronized void addUser(User user) throws UserCreationException {
        if (getUserByLogin(user.getLogin()) != null) {
            throw new UserCreationException("User login already exists");
        }
        if (getUserByEmail(user.getEmail()) != null) {
            throw new UserCreationException("User email already exists");
        }
        add(user);
    }

    public synchronized void updateUser(String login, User user) throws UserUpdateException {
        if (getUserByLogin(login) == null) {
            throw new UserUpdateException("User with given login does not exist");
        } else {
            delete(getUserByLogin(login));
            user.setLogin(login);
            add(user);
        }

    }
}
