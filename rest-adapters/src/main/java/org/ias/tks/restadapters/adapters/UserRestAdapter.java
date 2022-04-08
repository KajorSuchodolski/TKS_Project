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
    }

    @Override
    public UserOutputDto getUserById(UUID id) {
        return null;
    }

    @Override
    public UserOutputDto getUserByLogin(String login) {
        return null;
    }

    @Override
    public List<UserOutputDto> getAll() {
        return null;
    }

    @Override
    public UserOutputDto findByLoginPasswordActive(String login, String password) {
        return null;
    }

    @Override
    public List<UserOutputDto> searchUsersByLogin(String login) {
        return null;
    }

    @Override
    public void updateUser(String login, UserInputDto user) {

    }

    @Override
    public void activateUser(String login) {

    }

    @Override
    public void deactivateUser(String login) {

    }
}
