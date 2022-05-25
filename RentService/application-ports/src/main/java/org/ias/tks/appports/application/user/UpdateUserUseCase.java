package org.ias.tks.appports.application.user;

import org.ias.tks.appcore.domainmodel.model.user.User;

public interface UpdateUserUseCase {
    void updateUser(String login, User user);

    void activateUser(String login);

    void deactivateUser(String login);
}
