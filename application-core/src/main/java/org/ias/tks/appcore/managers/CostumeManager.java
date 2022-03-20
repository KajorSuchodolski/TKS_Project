package org.ias.tks.appcore.managers;

import org.ias.tks.appcore.entities.costume.Costume;
import org.ias.tks.appcore.entities.costume.CostumeSize;
import org.ias.tks.appcore.entities.costume.ForWhom;
import org.ias.tks.appcore.exceptions.CostumeByIdNotFound;
import org.ias.tks.appcore.exceptions.CostumeCreationException;
import org.ias.tks.appcore.exceptions.CostumeInUseException;
import org.ias.tks.appcore.exceptions.EntityValidationException;
import org.ias.tks.appcore.global_config.Validation;
import org.ias.tks.appcore.global_config.ValidationParameter;
import org.ias.tks.appcore.repositories.CostumeRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CostumeManager extends AbstractManager {

    private CostumeRepository costumeRepository;

    public CostumeManager() {
        super();
    }

    @Inject
    public void setCostumeRepository( CostumeRepository costumeRepository ) {
        this.costumeRepository = costumeRepository;
    }

    public CostumeRepository getCostumeRepository() {
        return costumeRepository;
    }

    // CREATE
    public void addCostume( Costume costume) throws CostumeCreationException {
        if(costume.getForWhom() == null) {
            throw new CostumeCreationException("ForWhom field is empty");
        }
        try {
            ForWhom forWhom = costume.getForWhom();
        }
        catch(IllegalArgumentException e) {
            throw new EntityValidationException("Invalid parameter for: ForWhom");
        }


        if(costume.getCostumeSize() == null) {
            throw new CostumeCreationException("CostumeSize field is empty");
        }
        try {
            CostumeSize costumeSize = costume.getCostumeSize();
        } catch(IllegalArgumentException e){
            throw new EntityValidationException("Invalid parameter for: CostumeSize");
        }


        if(costume.getName() == null) {
            throw new CostumeCreationException("Name field is null");
        }
        if( Validation.validateData(costume.getName(), ValidationParameter.COSTUME_NAME)) {
            throw new EntityValidationException("Costume name is invalid");
        }


        if(costume.getPrice() == 0 || costume.getPrice() < 0) {
            throw new CostumeCreationException("Price field is equal zero or less");
        }
        if(!Validation.validateData(Double.toString(costume.getPrice()), ValidationParameter.PRICE)) {
            throw new EntityValidationException("Price of the costume is invalid");
        }


        costumeRepository.addCostume(costume);
    }

    // READ

    public Costume getCostumeById(UUID id) throws CostumeByIdNotFound {
        return costumeRepository.getCostumeById(id);
    }

    public List<Costume> getAll() {
        return costumeRepository.getAll();
    }

    public List<Costume> getAllByRentStatus(boolean flag) { return costumeRepository.getAllByRentStatus(flag); }

    public List<Costume> searchAllCostumesByName(String name) {
        return costumeRepository.searchCostumesByName(name);
    }

    public List<Costume> getAllCostumesByAge(String forWhom) throws EntityValidationException {
        try {
            ForWhom parameterValidationChecker = ForWhom.valueOf(forWhom);
            return costumeRepository.getAllCostumesByAge(parameterValidationChecker);
        } catch(IllegalArgumentException e) {
            throw new EntityValidationException("Invalid parameter for enum type: ForWhom");
        }

    }

    public List<Costume> getAllCostumesByParams(String age, String size) throws EntityValidationException {
        try {
            CostumeSize costumeSize = CostumeSize.valueOf(size);
            ForWhom forWhom = ForWhom.valueOf(age);
            return costumeRepository.getAllCostumesByParams(forWhom, costumeSize);
        } catch(IllegalArgumentException e) {
            throw new EntityValidationException("Invalid parameter for ForWhom or CostumeSize");
        }

    }

    // UPDATE

    /*
    * TODO
    *  FIX PRICE VALIDATION
    *  FIX THIS FCKING VALIDATION
    * */
    public void updateCostume(UUID id, Costume costume) throws CostumeByIdNotFound, EntityValidationException {


        Costume tmpCostume = new Costume();

        if(costume.getName() != null) {
            if( Validation.validateData(costume.getName(), ValidationParameter.COSTUME_NAME)) {
                throw new EntityValidationException("Costume name is invalid");
            }
            tmpCostume.setName(costume.getName());
        }
        if(costume.getCostumeSize() != null) {
            try {
                CostumeSize costumeSize = costume.getCostumeSize();
            } catch(IllegalArgumentException e){
                throw new EntityValidationException("Invalid parameter for: CostumeSize");
            }
            tmpCostume.setCostumeSize(costume.getCostumeSize());
        }
        if(costume.getForWhom() != null) {
            try {
                ForWhom forWhom = costume.getForWhom();
            }
            catch(IllegalArgumentException e) {
                throw new EntityValidationException("Invalid parameter for: ForWhom");
            }
            tmpCostume.setForWhom(costume.getForWhom());
        }
        if(!Validation.validateData(Double.toString(costume.getPrice()), ValidationParameter.PRICE)) {
            throw new EntityValidationException("Price of the costume is invalid");
        } else {
            tmpCostume.setPrice(costume.getPrice());
        }
        costumeRepository.updateCostume(id, tmpCostume);
    }

    public void activateRent(UUID id) throws CostumeByIdNotFound {
        costumeRepository.activateRent(id);
    }

    public void deactivateRent(UUID id) throws CostumeByIdNotFound {
        costumeRepository.deactivateRent(id);
    }

    // DELETE
    public void removeCostume(UUID id) throws CostumeByIdNotFound, CostumeInUseException {
        costumeRepository.removeCostume(id);
    }
}
