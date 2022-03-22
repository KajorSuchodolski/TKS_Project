package infrastructure.repositories.user;

import org.ias.tks.appcore.entities.user.User;

public interface CreateUserPort {
    void addUser(User user);
}
