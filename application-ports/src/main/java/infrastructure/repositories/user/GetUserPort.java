package infrastructure.repositories.user;

import org.ias.tks.appcore.entities.user.User;


import java.util.List;
import java.util.UUID;

public interface GetUserPort {
    User getUserById(UUID id);

    User getUserByLogin(String login);

    List<User> getAll();

    User findByLoginPasswordActive(String login, String password);

    List<User> searchUsersByLogin(String login);
}
