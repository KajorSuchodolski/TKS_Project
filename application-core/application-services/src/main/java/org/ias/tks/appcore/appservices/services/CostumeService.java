package org.ias.tks.appcore.appservices.services;

import org.ias.tks.appcore.domainmodel.exceptions.CostumeByIdNotFound;
import org.ias.tks.appcore.domainmodel.exceptions.CostumeCreationException;
import org.ias.tks.appcore.domainmodel.exceptions.CostumeInUseException;
import org.ias.tks.appcore.domainmodel.exceptions.EntityValidationException;
import org.ias.tks.appcore.domainmodel.global_config.Validation;
import org.ias.tks.appcore.domainmodel.global_config.ValidationParameter;
import org.ias.tks.appcore.domainmodel.model.costume.Costume;
import org.ias.tks.appcore.domainmodel.model.costume.CostumeSize;
import org.ias.tks.appcore.domainmodel.model.costume.ForWhom;
import org.ias.tks.appports.application.costume.createCostumeUseCase;
import org.ias.tks.appports.application.costume.getCostumeUseCase;
import org.ias.tks.appports.application.costume.removeCostumeUseCase;
import org.ias.tks.appports.application.costume.updateCostumeUseCase;
import org.ias.tks.appports.infrastructure.costume.CreateCostumePort;
import org.ias.tks.appports.infrastructure.costume.GetCostumePort;
import org.ias.tks.appports.infrastructure.costume.RemoveCostumePort;
import org.ias.tks.appports.infrastructure.costume.UpdateCostumePort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CostumeService implements getCostumeUseCase, createCostumeUseCase, removeCostumeUseCase,updateCostumeUseCase {

    @Inject
    private CreateCostumePort createCostumePort;

    @Inject
    private GetCostumePort getCostumePort;

    @Inject
    private UpdateCostumePort updateCostumePort;

    @Inject
    private RemoveCostumePort removeCostumePort;

    public CostumeService() {
        super();
    }

    // CREATE
    @Override
    public void addCostume(Costume costume) throws CostumeCreationException {
        if (costume.getForWhom() == null) {
            throw new CostumeCreationException("ForWhom field is empty");
        }
        try {
            ForWhom forWhom = costume.getForWhom();
        } catch (IllegalArgumentException e) {
            throw new EntityValidationException("Invalid parameter for: ForWhom");
        }


        if (costume.getCostumeSize() == null) {
            throw new CostumeCreationException("CostumeSize field is empty");
        }
        try {
            CostumeSize costumeSize = costume.getCostumeSize();
        } catch (IllegalArgumentException e) {
            throw new EntityValidationException("Invalid parameter for: CostumeSize");
        }


        if (costume.getName() == null) {
            throw new CostumeCreationException("Name field is null");
        }
        if (Validation.validateData(costume.getName(), ValidationParameter.COSTUME_NAME)) {
            throw new EntityValidationException("Costume name is invalid");
        }


        if (costume.getPrice() == 0 || costume.getPrice() < 0) {
            throw new CostumeCreationException("Price field is equal zero or less");
        }
        if (!Validation.validateData(Double.toString(costume.getPrice()), ValidationParameter.PRICE)) {
            throw new EntityValidationException("Price of the costume is invalid");
        }


        createCostumePort.addCostume(costume);
    }

    // READ

    @Override
    public Costume getCostumeById(UUID id) throws CostumeByIdNotFound {
        return getCostumePort.getCostumeById(id);
    }

    @Override
    public List<Costume> getAll() {
        return getCostumePort.getAll();
    }

    @Override
    public List<Costume> getAllByRentStatus(boolean flag) {
        return getCostumePort.getAllByRentStatus(flag);
    }

    @Override
    public List<Costume> searchAllCostumesByName(String name) {
        return getCostumePort.searchAllCostumesByName(name);
    }

    @Override
    public List<Costume> getAllCostumesByAge(String forWhom) throws EntityValidationException {
        try {
            return getCostumePort.getAllCostumesByAge(forWhom);
        } catch (IllegalArgumentException e) {
            throw new EntityValidationException("Invalid parameter for enum type: ForWhom");
        }

    }
    @Override
    public List<Costume> getAllCostumesByParams(String age, String size) throws EntityValidationException {
        try {
            return getCostumePort.getAllCostumesByParams(age, size);
        } catch (IllegalArgumentException e) {
            throw new EntityValidationException("Invalid parameter for ForWhom or CostumeSize");
        }

    }


    // UPDATE

    /*
     * TODO
     *  FIX PRICE VALIDATION
     *  FIX THIS FCKING VALIDATION
     * */
    @Override
    public void updateCostume(UUID id, Costume costume) throws CostumeByIdNotFound, EntityValidationException {

        Costume tmpCostume = new Costume();

        if (costume.getName() != null) {
            if (Validation.validateData(costume.getName(), ValidationParameter.COSTUME_NAME)) {
                throw new EntityValidationException("Costume name is invalid");
            }
            tmpCostume.setName(costume.getName());
        }
        if (costume.getCostumeSize() != null) {
            try {
                CostumeSize costumeSize = costume.getCostumeSize();
            } catch (IllegalArgumentException e) {
                throw new EntityValidationException("Invalid parameter for: CostumeSize");
            }
            tmpCostume.setCostumeSize(costume.getCostumeSize());
        }
        if (costume.getForWhom() != null) {
            try {
                ForWhom forWhom = costume.getForWhom();
            } catch (IllegalArgumentException e) {
                throw new EntityValidationException("Invalid parameter for: ForWhom");
            }
            tmpCostume.setForWhom(costume.getForWhom());
        }
        if (!Validation.validateData(Double.toString(costume.getPrice()), ValidationParameter.PRICE)) {
            throw new EntityValidationException("Price of the costume is invalid");
        } else {
            tmpCostume.setPrice(costume.getPrice());
        }
        updateCostumePort.updateCostume(id, tmpCostume);
    }

    // DELETE
    @Override
    public void removeCostume(UUID id) {
        removeCostumePort.removeCostume(id);
    }
}
