package org.ias.tks.repositories.infrastructure.user;


import org.ias.tks.model.user.User;

public interface UpdateUserPort {
    void updateUser(String login, User user);

    void activateUser(String login);

    void deactivateUser(String login);
}
