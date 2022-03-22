package org.ias.tks.repositories.aggregates;
import org.ias.tks.exceptions.UserUpdateException;
import org.ias.tks.model.user.User;
import org.ias.tks.repositories.UserRepository;
import org.ias.tks.repositories.converters.UserConverter;
import org.ias.tks.repositories.infrastructure.user.CreateUserPort;
import org.ias.tks.repositories.infrastructure.user.GetUserPort;
import org.ias.tks.repositories.infrastructure.user.UpdateUserPort;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

public class UserRepositoryAdapter implements CreateUserPort, UpdateUserPort, GetUserPort {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserConverter converter;

    @Override
    public void addUser(User user) {
        userRepository.addUser(converter.convert(user));
    }

    @Override
    public void updateUser(String login, User user) throws UserUpdateException {
        userRepository.updateUser(login, converter.convert(user));
    }

    @Override
    public void activateUser(String login) {
        userRepository.activateUser(login);
    }

    @Override
    public void deactivateUser(String login) {
        userRepository.deactivateUser(login);
    }

    @Override
    public User getUserById(UUID id) {

    }

    @Override
    public User getUserByLogin(String login) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User findByLoginPasswordActive(String login, String password) {
        return null;
    }

    @Override
    public List<User> searchUsersByLogin(String login) {
        return null;
    }
}
