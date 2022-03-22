package org.ias.tks.repositories.infrastructure.user;

import org.ias.tks.model.user.User;


import java.util.List;
import java.util.UUID;

public interface GetUserPort {
    User getUserById(UUID id);

    User getUserByLogin(String login);

    List<User> getAll();

    User findByLoginPasswordActive(String login, String password);

    List<User> searchUsersByLogin(String login);
}
