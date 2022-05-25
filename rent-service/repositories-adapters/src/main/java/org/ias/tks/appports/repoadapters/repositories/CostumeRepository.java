package org.ias.tks.appports.repoadapters.repositories;


import org.ias.tks.appports.repoadapters.exceptions.CostumeByIdNotFound;
import org.ias.tks.appports.repoadapters.exceptions.CostumeInUseException;
import org.ias.tks.appports.repoadapters.entities.costume.CostumeEntity;
import org.ias.tks.appports.repoadapters.entities.costume.CostumeSizeEnt;
import org.ias.tks.appports.repoadapters.entities.costume.ForWhomEnt;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class CostumeRepository extends AbstractRepository<CostumeEntity> {

    public CostumeRepository() {
        CostumeEntity costume1 = new CostumeEntity(
                "Furry Costume",
                CostumeSizeEnt.XL,
                ForWhomEnt.BOYS,
                100
        );
        CostumeEntity costume2 = new CostumeEntity(
                "Furry Costume",
                CostumeSizeEnt.XL,
                ForWhomEnt.GIRLS,
                66
        );
        CostumeEntity costume3 = new CostumeEntity(
                "Pope",
                CostumeSizeEnt.XXL,
                ForWhomEnt.MAN,
                42
        );
        CostumeEntity costume4 = new CostumeEntity(
                "Amogus Red Impostor",
                CostumeSizeEnt.S,
                ForWhomEnt.BOYS,
                10
        );
        CostumeEntity costume5 = new CostumeEntity(
                "Zorro",
                CostumeSizeEnt.XL,
                ForWhomEnt.GIRLS,
                110
        );
        addCostume(costume1);
        addCostume(costume2);
        addCostume(costume3);
        addCostume(costume4);
        addCostume(costume5);

    }

    // READ

    public List<CostumeEntity> getAllByRentStatus(boolean flag) {
        return getAll()
                .stream()
                .filter(e -> e.isRented() == flag)
                .collect(Collectors.toList());
    }

    public List<CostumeEntity> getAllCostumesByAge(ForWhomEnt forWhom) {

        return getAll()
                .stream()
                .filter(e -> e.getForWhom().equals(forWhom))
                .collect(Collectors.toList());
    }

    public List<CostumeEntity> getAllCostumesByParams(ForWhomEnt forWhom, CostumeSizeEnt costumeSize) {
        return getAll()
                .stream()
                .filter(e -> e.getCostumeSize().equals(costumeSize) && e.getForWhom().equals(forWhom))
                .collect(Collectors.toList());
    }

    public CostumeEntity getCostumeById(UUID id) throws CostumeByIdNotFound {
        CostumeEntity tmpCostume = getById(id);
        if (tmpCostume == null) {
            throw new CostumeByIdNotFound();
        }
        return tmpCostume;
    }

    public List<CostumeEntity> searchCostumesByName(String name) {
        return getAll()
                .stream()
                .filter(e -> e.getName().contains(name))
                .collect(Collectors.toList());
    }


    // CREATE

    public synchronized void addCostume(CostumeEntity costume) {
        add(costume);
    }

    // DELETE

    public synchronized void removeCostume(UUID id) throws CostumeByIdNotFound, CostumeInUseException {
        if (getById(id) == null) {
            throw new CostumeByIdNotFound();
        }
        if (getById(id).isRented()) {
            throw new CostumeInUseException();
        }
        delete(getById(id));
    }

    // UPDATE

    public synchronized void updateCostume(UUID id, CostumeEntity costume) throws CostumeByIdNotFound {
        if (getById(id) == null) {
            throw new CostumeByIdNotFound();
        } else {
            delete(getById(id));
            costume.setId(id);
            add(costume);
        }
    }



}
