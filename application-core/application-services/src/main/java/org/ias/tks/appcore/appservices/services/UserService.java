package org.ias.tks.appcore.appservices.services;


import org.ias.tks.appcore.domainmodel.exceptions.*;
import org.ias.tks.appcore.domainmodel.global_config.Validation;
import org.ias.tks.appcore.domainmodel.global_config.ValidationParameter;
import org.ias.tks.appcore.domainmodel.model.user.User;
import org.ias.tks.appcore.domainmodel.model.user.access_levels.AccessLevelType;
import org.ias.tks.appcore.domainmodel.model.user.access_levels.Administrator;
import org.ias.tks.appcore.domainmodel.model.user.access_levels.Client;
import org.ias.tks.appcore.domainmodel.model.user.access_levels.Manager;
import org.ias.tks.appports.application.user.CreateUserUseCase;
import org.ias.tks.appports.application.user.GetUserUseCase;
import org.ias.tks.appports.application.user.UpdateUserUseCase;
import org.ias.tks.appports.infrastructure.user.CreateUserPort;
import org.ias.tks.appports.infrastructure.user.GetUserPort;
import org.ias.tks.appports.infrastructure.user.UpdateUserPort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;


@ApplicationScoped
public class UserService implements GetUserUseCase, CreateUserUseCase, UpdateUserUseCase {

    @Inject
    private CreateUserPort createUserPort;

    @Inject
    private GetUserPort getUserPort;

    @Inject
    private UpdateUserPort updateUserPort;
    // CREATE

    public void addUser(User user) throws UserCreationException {
        if (user.getLogin() == null) {
            throw new UserCreationException("Login field is empty");
        }
        if (user.getEmail() == null) {
            throw new UserCreationException("Email field is empty");
        }
        if (user.getPassword() == null) {
            throw new UserCreationException("Password field is empty");
        }
        if (user.getFirstName() == null) {
            throw new UserCreationException("Firstname field is empty");
        }
        if (user.getLastName() == null) {
            throw new UserCreationException("Lastname field is empty");
        }

        if (Validation.validateData(user.getFirstName(), ValidationParameter.FIRSTNAME)
                || Validation.validateData(user.getLastName(), ValidationParameter.LASTNAME)
                || Validation.validateData(user.getLogin(), ValidationParameter.LOGIN)
                || Validation.validateData(user.getPassword(), ValidationParameter.PASSWORD)
                || Validation.validateData(user.getEmail(), ValidationParameter.EMAIL)) {
            throw new UserCreationException("Sth is fucked up with validation");
        }
        createUserPort.addUser(user);
    }

    // READ
    @Override
    public User getUserById(UUID id) throws UserByIdNotFound {
        User user = getUserPort.getUserById(id);
        if (user == null) {
            throw new UserByIdNotFound();
        }
        return user;
    }

    @Override
    public User getUserByLogin(String login) throws UserByLoginNotFound {
        User user = getUserPort.getUserByLogin(login);
        if (user == null) {
            throw new UserByLoginNotFound();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        return getUserPort.getAll();
    }

    @Override
    public List<User> searchUsersByLogin(String login) {
        return getUserPort.searchUsersByLogin(login);
    }

    @Override
    public User findByLoginPasswordActive(String login, String password) throws UserByLoginNotFound {
        User user = getUserPort.findByLoginPasswordActive(login, password);
        if (user == null) {
            throw new UserByLoginNotFound();
        }
        return user;
    }

    // UPDATE

    public void updateUserPassword(String login, String password) throws EntityValidationException {
        getUserByLogin(login).setPassword(password);
    }

    public void updateUser(String login, User user) throws EntityValidationException {
        User tmpUser = new User();
        tmpUser.setLogin(login);
        switch (user.getAccessLevel()) {
            case "Admin" -> tmpUser.setAccessLevel(new Administrator(AccessLevelType.ADMINISTRATOR));
            case "Manager" -> tmpUser.setAccessLevel(new Manager(AccessLevelType.MANAGER));
            default -> tmpUser.setAccessLevel(new Client(AccessLevelType.CLIENT));
        }

        if (user.getFirstName() != null) {
            if (Validation.validateData(user.getFirstName(), ValidationParameter.FIRSTNAME)) {
                throw new EntityValidationException("User firstname is invalid");
            }
            tmpUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            if (Validation.validateData(user.getLastName(), ValidationParameter.LASTNAME)) {
                throw new EntityValidationException("User lastname is invalid");
            }
            tmpUser.setLastName(user.getLastName());
        }
        if (user.getPassword() != null) {
            if (Validation.validateData(user.getPassword(), ValidationParameter.PASSWORD)) {
                throw new EntityValidationException("User password is invalid");
            }
            tmpUser.setPassword(user.getPassword());
        }
        if (user.getEmail() != null) {
            if (Validation.validateData(user.getEmail(), ValidationParameter.EMAIL)) {
                throw new EntityValidationException("User email is invalid");
            }
            tmpUser.setEmail(user.getEmail());
        }
        System.out.println("juz prawie" + user);
        updateUserPort.updateUser(login, tmpUser);

    }

    public void activateUser(String login) throws UserByLoginNotFound {
        updateUserPort.activateUser(login);
    }

    public void deactivateUser(String login) throws UserByLoginNotFound {
        updateUserPort.deactivateUser(login);
    }

}
