package org.ias.tks.apports.repoadapters.tests;

import org.ias.tks.appports.repoadapters.entities.costume.CostumeEntity;
import org.ias.tks.appports.repoadapters.entities.rent.RentEntity;
import org.ias.tks.appports.repoadapters.repositories.CostumeRepository;
import org.ias.tks.appports.repoadapters.repositories.RentRepository;
import org.ias.tks.appports.repoadapters.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RentRepositoryTest {

    private CostumeRepository costumeRepo;
    private UserRepository userRepo;
    private RentRepository rentRepo;

    @BeforeEach
    void initRepository() {
        rentRepo = new RentRepository();
        costumeRepo = new CostumeRepository();
        userRepo = new UserRepository();

        List<CostumeEntity> costumes1 = new ArrayList<>();
        List<CostumeEntity> costumes2 = new ArrayList<>();

        costumes1.add(costumeRepo.getAll().get(0));
        costumes2.add(costumeRepo.getAll().get(1));
        costumes2.add(costumeRepo.getAll().get(2));

        rentRepo.add(new RentEntity(userRepo.getAll().get(0), costumes1, 100,
                LocalDate.of(2022, 5, 3)));
        rentRepo.add(new RentEntity(userRepo.getAll().get(1), costumes2, 99,
                LocalDate.of(2022, 6, 4)));
    }

    @Test
    void getRentsByCustomerTest() {
        Assertions.assertEquals(rentRepo.getAll().get(0),
                rentRepo.getRentsByCustomer(userRepo.getAll().get(0).getLogin()).get(0));
    }

    @Test
    void getRentByIdTest() {
        UUID id = rentRepo.getAll().get(0).getId();
        Assertions.assertEquals(rentRepo.getAll().get(0),
                rentRepo.getRentById(id));
    }

    @Test
    void endRentTest() {
        String date = "2022-07-05";
        UUID id = rentRepo.getAll().get(0).getId();
        rentRepo.endRent(date, id);
        Assertions.assertEquals(LocalDate.parse(date), rentRepo.getAll().get(0).getEndTime());
    }

    @Test
    void setRentedCostumesToNotRentedTest() {
        UUID id = rentRepo.getAll().get(0).getId();
        rentRepo.setRentedCostumesToNotRented(id);
        Assertions.assertFalse(costumeRepo.getAll().get(0).isRented());
    }

    @Test
    void userCurrentRentsTest() {
        Assertions.assertEquals(rentRepo.getAll().get(0),
                rentRepo.userCurrentRents(userRepo.getAll().get(0).getLogin()).get(0));
    }

    @Test
    void userPastRentsTest() {
        rentRepo.endRent("now", rentRepo.getAll().get(0).getId());
        Assertions.assertEquals(rentRepo.getAll().get(0),
                rentRepo.userPastRents(userRepo.getAll().get(0).getLogin()).get(0));
    }

    @Test
    void getCostumeAllocationsTest() {
        UUID id = costumeRepo.getAll().get(0).getId();
        Assertions.assertEquals(rentRepo.getAll().get(0), rentRepo.getCostumeAllocations(id).get(0));
    }

}
