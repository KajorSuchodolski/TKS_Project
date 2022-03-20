package org.ias.tks.appcore.managers;

import org.ias.tks.appcore.entities.user.User;
import org.ias.tks.appcore.entities.user.access_levels.AccessLevelType;
import org.ias.tks.appcore.entities.user.access_levels.Administrator;
import org.ias.tks.appcore.entities.user.access_levels.Client;
import org.ias.tks.appcore.exceptions.*;
import org.ias.tks.appcore.global_config.Validation;
import org.ias.tks.appcore.global_config.ValidationParameter;
import org.ias.tks.appcore.repositories.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;


@ApplicationScoped
public class UserManager extends AbstractManager {

    private UserRepository userRepository;

    @Inject
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // CREATE

    /*
    *
    * TODO
    *  FIX VALIADATION
    *
    *
    * */

    public void addUser( User user) throws UserCreationException {
        if( user.getLogin() == null ) {
            throw new UserCreationException("Login field is empty");
        }
        if( user.getEmail() == null ) {
            throw new UserCreationException("Email field is empty");
        }
        if( user.getPassword() == null ) {
            throw new UserCreationException("Password field is empty");
        }
        if( user.getFirstName() == null ) {
            throw new UserCreationException("Firstname field is empty");
        }
        if( user.getLastName() == null ) {
            throw new UserCreationException("Lastname field is empty");
        }

        if(Validation.validateData(user.getFirstName(), ValidationParameter.FIRSTNAME)
                || Validation.validateData(user.getLastName(), ValidationParameter.LASTNAME)
                || Validation.validateData(user.getLogin(), ValidationParameter.LOGIN)
                || Validation.validateData(user.getPassword(), ValidationParameter.PASSWORD)
                || Validation.validateData(user.getEmail(), ValidationParameter.EMAIL)) {
            throw new UserCreationException("Sth is fucked up with validation");
        }
        userRepository.addUser(user);
    }

    // READ

    public User getUserById(UUID id) throws UserByIdNotFound {
        User user = userRepository.getById(id);
        if(user == null) {
            throw new UserByIdNotFound();
        }
        return user;
    }

    public User getUserByLogin(String login) throws UserByLoginNotFound {
        User user = userRepository.getUserByLogin(login);
        if(user == null) {
            throw new UserByLoginNotFound();
        }
        return user;
    }

    public List<User> getAll() {
       return userRepository.getAll();
    }

    public List<User> searchUsersByLogin(String login) {
        return userRepository.searchUsersByLogin(login);
    }

    public User findByLoginPasswordActive(String login, String password) throws UserByLoginNotFound {
        User user = userRepository.findByLoginPasswordActive(login, password);
        if(user == null) {
            throw new UserByLoginNotFound();
        }
        return user;
    }

    // UPDATE

    public void updateUserPassword(String login, String password) throws EntityValidationException, UserUpdateException {
        getUserByLogin(login).setPassword(password);
    }

    public void updateUser(String login, User user) throws EntityValidationException, UserUpdateException {
        User tmpUser = new User();
        tmpUser.setLogin(login);
        switch(user.getAccessLevel()) {
            case "Admin" -> tmpUser.setAccessLevel(new Administrator(AccessLevelType.ADMINISTRATOR));
            case "Manager" -> tmpUser.setAccessLevel(new Administrator(AccessLevelType.MANAGER));
            case "Client" -> tmpUser.setAccessLevel(new Administrator(AccessLevelType.CLIENT));
            default -> tmpUser.setAccessLevel(new Client(AccessLevelType.CLIENT));
        }

        if(user.getFirstName() != null) {
            if(Validation.validateData(user.getFirstName(), ValidationParameter.FIRSTNAME) ) {
                throw new EntityValidationException("User firstname is invalid");
            }
            tmpUser.setFirstName(user.getFirstName());
        }
        if(user.getLastName() != null) {
            if(Validation.validateData(user.getLastName(), ValidationParameter.LASTNAME) ) {
                throw new EntityValidationException("User lastname is invalid");
            }
            tmpUser.setLastName(user.getLastName());
        }
        if(user.getPassword() != null) {
            if(Validation.validateData(user.getPassword(), ValidationParameter.PASSWORD) ) {
                throw new EntityValidationException("User password is invalid");
            }
            tmpUser.setPassword(user.getPassword());
        }
        if(user.getEmail() != null) {
            if(Validation.validateData(user.getEmail(), ValidationParameter.EMAIL)) {
                throw new EntityValidationException("User email is invalid");
            }
            tmpUser.setEmail(user.getEmail());
        }
        System.out.println("juz prawie" + user);
        userRepository.updateUser(login, tmpUser);

    }

    public void activateUser(String login) throws UserByLoginNotFound {
        userRepository.activateUser(login);
    }

    public void deactivateUser(String login) throws UserByLoginNotFound {
        userRepository.deactivateUser(login);
    }

//    public User findUserByLoginPasswordActive(String login, String password) throws UserByLoginNotFound {
//        //do napisania, karbo nie pokazał :(
//    }

    /*public User findByLoginPasswordActive(String login, String password){
        System.out.println("Searching for login: ", + login + "and password:" + password);
        return userRepository.
    }*/



}
