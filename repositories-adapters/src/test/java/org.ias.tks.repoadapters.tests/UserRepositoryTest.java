package org.ias.tks.repoadapters.tests;

import org.ias.tks.appports.repoadapters.entities.user.UserEntity;
import org.ias.tks.appports.repoadapters.entities.user.access_levels.AccessLevelTypeEntity;
import org.ias.tks.appports.repoadapters.entities.user.access_levels.ClientEntity;
import org.ias.tks.appports.repoadapters.entities.user.access_levels.ManagerEntity;
import org.ias.tks.appports.repoadapters.exceptions.UserUpdateException;
import org.ias.tks.appports.repoadapters.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class UserRepositoryTest {

    private UserRepository userRepo;

    private UserEntity user1 = new UserEntity("Janusz", "Kowal", "jk1234",
            "admin12", "janusz@gmail.com", new ManagerEntity(AccessLevelTypeEntity.ADMINISTRATOR));
    private UserEntity user2 = new UserEntity("Janina", "Tuszyńska", "jt1234",
            "admin123", "janina@gmail.com", new ManagerEntity(AccessLevelTypeEntity.CLIENT));
    private UserEntity user3 = new UserEntity("Katarzyna", "Nowak", "kn124",
            "admin123", "kasia@gmail.com", new ManagerEntity(AccessLevelTypeEntity.MANAGER));

    @BeforeEach
    void initRepository() {
        userRepo = new UserRepository();
        userRepo.addUser(user1);
        userRepo.addUser(user2);
        userRepo.addUser(user3);
    }

    @Test
    void deactivateUserTest() {
        assertTrue(user1.isActive());
        userRepo.deactivateUser("jk1234");
        assertFalse(user1.isActive());
    }

    @Test
    void activateUserTest() {
        userRepo.deactivateUser("jk1234");
        assertFalse(user1.isActive());
        userRepo.activateUser("jk1234");
        assertTrue(user1.isActive());

    }

    @Test
    void getUserByLoginTest() {
        UserEntity e = userRepo.getUserByLogin("jk1234");
        assertEquals("janusz@gmail.com", e.getEmail());
    }

    @Test
    void searchUsersByLoginTest() {
        List<UserEntity> e = userRepo.searchUsersByLogin("1234");
        assertEquals(e.size(), 2);
    }

    @Test
    void findByLoginPasswordActiveTest() {
        UserEntity user = userRepo.findByLoginPasswordActive("jk1234", "admin12");
        assertEquals(user1.isActive(), user.isActive());
    }

    @Test
    void addUserTest() {
        userRepo.addUser(new UserEntity("Anna", "Maria", "am123",
                "anna123", "annamaria@gmail.com", new ClientEntity(AccessLevelTypeEntity.CLIENT)));
        assertEquals(7, userRepo.getAll().size());
    }

    @Test
    void updateUserTest() throws UserUpdateException {
        UserEntity updatedUser1 = new UserEntity("Janusz", "Szymczyk", "jk1234",
                "admin12", "janusz@gmail.com", new ManagerEntity(AccessLevelTypeEntity.ADMINISTRATOR));
        userRepo.updateUser("jk1234", updatedUser1);

        UserEntity afterUpdateUser1 = userRepo.getUserByLogin("jk1234");
        assertEquals("Szymczyk", afterUpdateUser1.getLastName());
    }
}
