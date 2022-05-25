package org.ias.tks.soapadapters.adapters;

import org.ias.tks.appports.application.UserUseCases;
import org.ias.tks.soapadapters.dto.user.UserInputSOAP;
import org.ias.tks.soapadapters.dto.user.UserOutputSOAP;
import org.ias.tks.soapadapters.mappers.UserMapper;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class UserSOAPAdapter {

    @Inject
    private UserUseCases userUseCases;

    @Inject
    private UserMapper userMapper;


    public void addUser(UserInputSOAP user) {
        userUseCases.addUser(userMapper.mapToUser(user));
    }


    public UserOutputSOAP getUserById(UUID id) {
        return userMapper.mapToUserOutputSOAP(userUseCases.getUserById(id));
    }

    //no nie powinno sie tak robic
    public UserInputSOAP getUserByIdXD(UUID id) {
        return userMapper.mapToUserInputSOAP(userUseCases.getUserById(id));
    }

    public UserOutputSOAP getUserByLogin(String login) {
        return userMapper.mapToUserOutputSOAP(userUseCases.getUserByLogin(login));
    }

    public List<UserOutputSOAP> getAll() {
        return userMapper.mapToUserSOAPList(userUseCases.getAll());
    }

    public UserOutputSOAP findByLoginPasswordActive(String login, String password) {
        return userMapper.mapToUserOutputSOAP(userUseCases.findByLoginPasswordActive(login, password));
    }


    public List<UserOutputSOAP> searchUsersByLogin(String login) {
        return userMapper.mapToUserSOAPList(userUseCases.searchUsersByLogin(login));
    }


    public void updateUser(String login, UserInputSOAP user) {
        userUseCases.updateUser(login, userMapper.mapToUser(user));
    }


    public void activateUser(String login) {
        userUseCases.activateUser(login);
    }


    public void deactivateUser(String login) {
        userUseCases.deactivateUser(login);
    }
}
