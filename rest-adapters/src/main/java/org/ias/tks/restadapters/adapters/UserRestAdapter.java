package org.ias.tks.restadapters.adapters;

import org.ias.tks.appports.application.UserUseCases;
import org.ias.tks.restadapters.dto.user.UserInputDto;
import org.ias.tks.restadapters.dto.user.UserOutputDto;
import org.ias.tks.restadapters.mappers.UserMapper;
import org.ias.tks.restadapters.ports.UserRestPorts;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class UserRestAdapter implements UserRestPorts {

    @Inject
    private UserUseCases userUseCases;

    @Inject
    private UserMapper userMapper;

    @Override
    public void addUser(UserInputDto user) {
        userUseCases.addUser(userMapper.mapToUser(user));
    }

    @Override
    public UserOutputDto getUserById(UUID id) {
        return userMapper.mapToUserOutputDTO(userUseCases.getUserById(id));
    }

    @Override
    public UserOutputDto getUserByLogin(String login) {
        return userMapper.mapToUserOutputDTO(userUseCases.getUserByLogin(login));
    }

    @Override
    public List<UserOutputDto> getAll() {
        return userMapper.mapToUserDTOList(userUseCases.getAll());
    }

    @Override
    public UserOutputDto findByLoginPasswordActive(String login, String password) {
        return userMapper.mapToUserOutputDTO(userUseCases.findByLoginPasswordActive(login, password));
    }

    @Override
    public List<UserOutputDto> searchUsersByLogin(String login) {
        return userMapper.mapToUserDTOList(userUseCases.searchUsersByLogin(login));
    }

    @Override
    public void updateUser(String login, UserInputDto user) {
        userUseCases.updateUser(login, userMapper.mapToUser(user));
    }

    @Override
    public void activateUser(String login) {
        userUseCases.activateUser(login);
    }

    @Override
    public void deactivateUser(String login) {
        userUseCases.deactivateUser(login);
    }
}
