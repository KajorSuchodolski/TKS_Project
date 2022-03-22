package infrastructure.repositories.user;

import org.ias.tks.appcore.entities.user.User;

public interface UpdateUserPort {
    void updateUser(String login, User user);

    void activateUser(String login);

    void deactivateUser(String login);
}
