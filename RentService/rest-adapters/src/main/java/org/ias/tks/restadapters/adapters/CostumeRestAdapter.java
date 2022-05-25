package org.ias.tks.restadapters.adapters;

import org.ias.tks.appports.application.CostumeUseCases;
import org.ias.tks.restadapters.dto.costume.CostumeDTO;
import org.ias.tks.restadapters.mappers.CostumeMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class CostumeRestAdapter {

    @Inject
    private CostumeUseCases costumeUseCases;

    @Inject
    private CostumeMapper costumeMapper;


    public void addCostume(CostumeDTO costume) {
        costumeUseCases.addCostume(costumeMapper.mapToCostume(costume));
    }

    public CostumeDTO getCostumeById(UUID id) {
        return costumeMapper.mapToCostumeDTO(costumeUseCases.getCostumeById(id));
    }

    public List<CostumeDTO> getAll() {
        return costumeMapper.mapToCostumeDTOList(costumeUseCases.getAll());
    }

    public List<CostumeDTO> getAllByRentStatus(boolean flag) {
        return costumeMapper.mapToCostumeDTOList(costumeUseCases.getAllByRentStatus(flag));
    }

    public List<CostumeDTO> getAllCostumesByAge(String forWhom) {
        return costumeMapper.mapToCostumeDTOList(costumeUseCases.getAllCostumesByAge(forWhom));
    }

    public List<CostumeDTO> getAllCostumesByParams(String age, String size) {
        return costumeMapper.mapToCostumeDTOList(costumeUseCases.getAllCostumesByParams(age, size));
    }

    public List<CostumeDTO> searchAllCostumesByName(String name) {
        return costumeMapper.mapToCostumeDTOList(costumeUseCases.searchAllCostumesByName(name));
    }

    public void removeCostume(UUID id) {
        costumeUseCases.removeCostume(id);
    }

    public void updateCostume(UUID id, CostumeDTO costume) {
        costumeUseCases.updateCostume(id, costumeMapper.mapToCostume(costume));
    }
}
