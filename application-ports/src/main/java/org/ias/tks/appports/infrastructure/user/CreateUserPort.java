package org.ias.tks.appports.infrastructure.user;


import org.ias.tks.appcore.domainmodel.model.user.User;

public interface CreateUserPort {
    void addUser(User user);
}
