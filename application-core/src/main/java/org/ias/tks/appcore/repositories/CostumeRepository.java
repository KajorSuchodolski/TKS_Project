package org.ias.tks.appcore.repositories;

import org.ias.tks.appcore.entities.costume.Costume;
import org.ias.tks.appcore.entities.costume.CostumeSize;
import org.ias.tks.appcore.entities.costume.ForWhom;
import org.ias.tks.appcore.exceptions.CostumeByIdNotFound;
import org.ias.tks.appcore.exceptions.CostumeInUseException;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class CostumeRepository extends AbstractRepository<Costume> {

    public CostumeRepository() {
        Costume costume1 = new Costume(
                "Furry Costume",
                CostumeSize.XL,
                ForWhom.BOYS,
                100
        );
        Costume costume2 = new Costume(
                "Furry Costume",
                CostumeSize.XL,
                ForWhom.GIRLS,
                66
        );
        Costume costume3 = new Costume(
                "Pope",
                CostumeSize.XXL,
                ForWhom.MAN,
                42
        );
        Costume costume4 = new Costume(
                "Amogus Red Impostor",
                CostumeSize.S,
                ForWhom.BOYS,
                10
        );
        Costume costume5 = new Costume(
                "Zorro",
                CostumeSize.XL,
                ForWhom.GIRLS,
                100
        );
        addCostume(costume1);
        addCostume(costume2);
        addCostume(costume3);
        addCostume(costume4);
        addCostume(costume5);
    }

    // READ

    public List<Costume> getAllByRentStatus(boolean flag) {
        return getAll()
                .stream()
                .filter(e -> e.isRented() == flag)
                .collect(Collectors.toList());
    }

    public List<Costume> getAllCostumesByAge(ForWhom forWhom) {

        return getAll()
                .stream()
                .filter(e -> e.getForWhom().equals(forWhom))
                .collect(Collectors.toList());
    }

    public List<Costume> getAllCostumesByParams(ForWhom forWhom, CostumeSize costumeSize) {
        return getAll()
                .stream()
                .filter(e -> e.getCostumeSize().equals(costumeSize) && e.getForWhom().equals(forWhom))
                .collect(Collectors.toList());
    }

    public Costume getCostumeById(UUID id) throws CostumeByIdNotFound {
        Costume tmpCostume = getById(id);
        if(tmpCostume == null) {
            throw new CostumeByIdNotFound();
        }
        return tmpCostume;
    }

    public List<Costume> searchCostumesByName(String name) {
        return getAll()
                .stream()
                .filter(e -> e.getName().contains(name))
                .collect(Collectors.toList());
    }



    // CREATE

    public synchronized void addCostume(Costume costume) {
        add(costume);
    }

    // DELETE

    public synchronized void removeCostume(UUID id) throws CostumeByIdNotFound, CostumeInUseException {
        if(getById(id) == null) {
            throw new CostumeByIdNotFound();
        }
        if(getById(id).isRented()) {
            throw new CostumeInUseException();
        }
        delete(getById(id));
    }

    // UPDATE

    public synchronized void updateCostume(UUID id, Costume costume) throws CostumeByIdNotFound {
        if(getById(id) == null) {
            throw new CostumeByIdNotFound();
        } else {
            delete(getById(id));
            costume.setId(id);
            add(costume);
        }
    }

    public synchronized void activateRent(UUID id) throws CostumeByIdNotFound {
        getCostumeById(id).setRented(true);
    }

    public synchronized void deactivateRent(UUID id) throws CostumeByIdNotFound {
        getCostumeById(id).setRented(false);
    }



}
