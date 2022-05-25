package org.ias.tks.soapadapters.adapters;

import org.ias.tks.appports.application.CostumeUseCases;
import org.ias.tks.soapadapters.dto.costume.CostumeInputSOAP;
import org.ias.tks.soapadapters.dto.costume.CostumeOutputSOAP;
import org.ias.tks.soapadapters.mappers.CostumeMapper;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class CostumeSOAPAdapter {

    @Inject
    private CostumeUseCases costumeUseCases;

    @Inject
    private CostumeMapper costumeMapper;


    public void addCostume(CostumeInputSOAP costume) {
        costumeUseCases.addCostume(costumeMapper.mapToCostume(costume));
    }

    public CostumeOutputSOAP getCostumeById(UUID id) {
        return costumeMapper.mapToCostumeOutputSOAP(costumeUseCases.getCostumeById(id));
    }

    public List<CostumeOutputSOAP> getAllToSOAP() {
        return costumeMapper.mapToCostumeSOAPList(costumeUseCases.getAll());
    }

    public List<CostumeOutputSOAP> getAllByRentStatus(boolean flag) {
        return costumeMapper.mapToCostumeSOAPList(costumeUseCases.getAllByRentStatus(flag));
    }

    public List<CostumeOutputSOAP> getAllCostumesByAge(String forWhom) {
        return costumeMapper.mapToCostumeSOAPList(costumeUseCases.getAllCostumesByAge(forWhom));
    }


    public void removeCostume(UUID id) {
        costumeUseCases.removeCostume(id);
    }

    public void updateCostume(UUID id, CostumeInputSOAP costume) {
        costumeUseCases.updateCostume(id, costumeMapper.mapToCostume(costume));
    }
}
