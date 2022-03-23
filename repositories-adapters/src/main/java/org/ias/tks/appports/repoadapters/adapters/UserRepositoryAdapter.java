package org.ias.tks.appports.repoadapters.adapters;

import org.ias.tks.appcore.domainmodel.model.user.User;
import org.ias.tks.appports.infrastructure.user.CreateUserPort;
import org.ias.tks.appports.infrastructure.user.GetUserPort;
import org.ias.tks.appports.infrastructure.user.UpdateUserPort;
import org.ias.tks.appports.repoadapters.exceptions.UserUpdateException;
import org.ias.tks.appports.repoadapters.mappers.UserMapper;
import org.ias.tks.appports.repoadapters.repositories.UserRepository;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserRepositoryAdapter implements CreateUserPort, GetUserPort, UpdateUserPort {

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        userRepository.addUser(userMapper.mapToUserEntity(user));
    }


    @Override
    public User getUserById(UUID id) {
        return userMapper.mapToUser(userRepository.getById(id));
    }

    @Override
    public User getUserByLogin(String login) {
        return userMapper.mapToUser(userRepository.getUserByLogin(login));
    }

    @Override
    public List<User> getAll() {
        return userMapper.mapToUserList(userRepository.getAll());
    }

    @Override
    public User findByLoginPasswordActive(String login, String password) {
        return userMapper.mapToUser(userRepository.findByLoginPasswordActive(login,password));
    }

    @Override
    public List<User> searchUsersByLogin(String login) {
        return userMapper.mapToUserList(userRepository.searchUsersByLogin(login));
    }

    @Override
    public void updateUser(String login, User user) {
        try {
            userRepository.updateUser(login,userMapper.mapToUserEntity(user));
        } catch (UserUpdateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void activateUser(String login) {
        userRepository.activateUser(login);

    }

    @Override
    public void deactivateUser(String login) {
        userRepository.deactivateUser(login);
    }
}
