package org.ias.tks.appports.application.user;

import org.ias.tks.appcore.domainmodel.exceptions.UserByIdNotFound;
import org.ias.tks.appcore.domainmodel.model.user.User;

import java.util.List;
import java.util.UUID;

public interface GetUserUseCase {
    User getUserById(UUID id) throws UserByIdNotFound;

    User getUserByLogin(String login);

    List<User> getAll();

    User findByLoginPasswordActive(String login, String password);

    List<User> searchUsersByLogin(String login);
}
