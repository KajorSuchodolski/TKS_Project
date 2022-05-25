package org.ias.tks.appports.application.user;

import org.ias.tks.appcore.domainmodel.model.user.User;

public interface CreateUserUseCase {
    void addUser(User user);
}
