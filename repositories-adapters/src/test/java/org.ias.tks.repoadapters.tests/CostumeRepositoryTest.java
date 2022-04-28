package org.ias.tks.repoadapters.tests;

import org.ias.tks.appports.repoadapters.entities.costume.CostumeEntity;
import org.ias.tks.appports.repoadapters.entities.costume.CostumeSizeEnt;
import org.ias.tks.appports.repoadapters.entities.costume.ForWhomEnt;
import org.ias.tks.appports.repoadapters.exceptions.CostumeInUseException;
import org.ias.tks.appports.repoadapters.repositories.CostumeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CostumeRepositoryTest {

    private CostumeRepository costumeRepo;

    private CostumeEntity costume1 = new CostumeEntity("Nun", CostumeSizeEnt.XL, ForWhomEnt.GIRLS, 99);
    private CostumeEntity costume2 = new CostumeEntity("Bee", CostumeSizeEnt.M, ForWhomEnt.WOMEN, 50);
    private CostumeEntity costume3 = new CostumeEntity("Bee", CostumeSizeEnt.M, ForWhomEnt.WOMEN, 49);

    @BeforeEach
    void initRepository() {
        costumeRepo = new CostumeRepository();
        costumeRepo.addCostume(costume1);
        costumeRepo.addCostume(costume2);
        costumeRepo.addCostume(costume3);
    }

    @Test
    void getAllByRentStatusTest() {
        costume1.setRented(true);
        Assertions.assertEquals(7, costumeRepo.getAllByRentStatus(false).size());
    }

    @Test
    void getAllByAgeTest() {
        Assertions.assertEquals(3, costumeRepo.getAllCostumesByAge(ForWhomEnt.GIRLS).size());
    }

    @Test
    void getAllByParamsTest() {
        Assertions.assertEquals(3,
                costumeRepo.getAllCostumesByParams(ForWhomEnt.GIRLS, CostumeSizeEnt.XL).size());
    }

    @Test
    void getByIdTest() {
        UUID costumeId = costume1.getId();
        Assertions.assertEquals(costume1.getPrice(), costumeRepo.getCostumeById(costumeId).getPrice());
    }

    @Test
    void searchByNameTest() {
        Assertions.assertEquals(2, costumeRepo.searchCostumesByName("Bee").size());
    }

    @Test
    void addCostumeTest() {
        costumeRepo.addCostume(new CostumeEntity("Cat", CostumeSizeEnt.M, ForWhomEnt.WOMEN, 99));
        assertEquals(9, costumeRepo.getAll().size());
    }

    @Test
    void updateCostumeTest() {
        CostumeEntity updatedCostume1 = new CostumeEntity("Priest", CostumeSizeEnt.XL, ForWhomEnt.GIRLS, 99);

        costumeRepo.updateCostume(costume1.getId(), updatedCostume1);

        CostumeEntity afterUpdateCostume1 = costumeRepo.getCostumeById(costume1.getId());
        assertEquals("Priest", afterUpdateCostume1.getName());
    }

    @Test
    void removeCostumeTest() throws CostumeInUseException {
        costumeRepo.removeCostume(costume1.getId());
        assertEquals(7, costumeRepo.getAll().size());
    }
}
