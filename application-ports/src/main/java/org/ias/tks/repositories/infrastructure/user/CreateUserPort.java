package org.ias.tks.repositories.infrastructure.user;

import org.ias.tks.model.user.User;

public interface CreateUserPort {
    void addUser(User user);
}
