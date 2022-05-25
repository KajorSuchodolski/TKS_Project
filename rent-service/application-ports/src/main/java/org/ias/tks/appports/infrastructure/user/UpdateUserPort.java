package org.ias.tks.appports.infrastructure.user;


import org.ias.tks.appcore.domainmodel.model.user.User;

public interface UpdateUserPort {
    void updateUser(String login, User user);

    void activateUser(String login);

    void deactivateUser(String login);
}
