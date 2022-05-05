package org.ias.tks.soapadapters.services;

import org.ias.tks.appcore.domainmodel.exceptions.EntityValidationException;
import org.ias.tks.appcore.domainmodel.exceptions.UserByLoginNotFound;
import org.ias.tks.appcore.domainmodel.exceptions.UserCreationException;
import org.ias.tks.soapadapters.adapters.UserSOAPAdapter;
import org.ias.tks.soapadapters.dto.user.UserInputSOAP;
import org.ias.tks.soapadapters.dto.user.UserOutputSOAP;
import org.ias.tks.soapadapters.mappers.UserMapper;

import javax.inject.Inject;
import javax.jws.WebService;


import java.util.List;
import java.util.UUID;

@WebService(serviceName = "usersoapapi")
public class UserSoapService {

    @Inject
    UserSOAPAdapter userSOAPAdapter;

    @Inject
    UserMapper userMapper;

    public String ping(){
        return "pong";
    }

    public List<UserOutputSOAP> getAllUsers(){
        return userSOAPAdapter.getAll();
    }

    public UserOutputSOAP getUser(String id){
        return userSOAPAdapter.getUserById(UUID.fromString(id));
    }


    public UserOutputSOAP getUserByLogin(String login) {
        if (login == null || login.trim().equals("")) {
            throw new RuntimeException();
        }
        try {
            return userSOAPAdapter.getUserByLogin(login);
        } catch (UserByLoginNotFound e) {
            throw new RuntimeException();
        }

    }


    public UserInputSOAP addUser(UserInputSOAP user) {
        if (user == null) {
            throw new RuntimeException();
        }
        try {
            userSOAPAdapter.addUser(user);
            return user;
        } catch (UserCreationException e) {
            throw new RuntimeException();
        }
    }

    public UserOutputSOAP updateUser(String login, UserInputSOAP user) {
        if (login == null || login.trim().equals("")) {
            throw new RuntimeException();
        }
        if (user == null) {
            throw new RuntimeException();
        }
        try {
            System.out.println(user.getAccessLevel());
            userSOAPAdapter.updateUser(login, user);
            return userSOAPAdapter.getUserByLogin(login);

        } catch (EntityValidationException e) {
            throw new RuntimeException();
        }
    }

    public UserOutputSOAP activateUser(String login) {
        if (login == null || login.trim().equals("")) {
            throw new RuntimeException();
        }
        try {
            userSOAPAdapter.activateUser(login);
            return userSOAPAdapter.getUserByLogin(login);

        } catch (UserByLoginNotFound e) {
            throw new RuntimeException();
        }
    }

    public UserOutputSOAP deactivateUser(String login) {
        if (login == null || login.trim().equals("")) {
            throw new RuntimeException();
        }
        try {
            userSOAPAdapter.deactivateUser(login);
            return userSOAPAdapter.getUserByLogin(login);

        } catch (UserByLoginNotFound e) {
            throw new RuntimeException();
        }
    }

}
