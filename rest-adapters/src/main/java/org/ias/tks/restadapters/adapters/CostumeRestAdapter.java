package org.ias.tks.restadapters.adapters;

import org.ias.tks.appports.application.CostumeUseCases;
import org.ias.tks.restadapters.dto.costume.CostumeDTO;
import org.ias.tks.restadapters.mappers.CostumeMapper;
import org.ias.tks.restadapters.ports.CostumeRestPorts;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class CostumeRestAdapter implements CostumeRestPorts {

    @Inject
    private CostumeUseCases costumeUseCases;

    @Inject
    private CostumeMapper costumeMapper;


    @Override
    public void addCostume(CostumeDTO costume) {
        costumeUseCases.addCostume(costumeMapper.mapToCostume(costume));
    }

    @Override
    public CostumeDTO getCostumeById(UUID id) {
        return costumeMapper.mapToCostumeDTO(costumeUseCases.getCostumeById(id));
    }

    @Override
    public List<CostumeDTO> getAll() {
        return costumeMapper.mapToCostumeDTOList(costumeUseCases.getAll());
    }

    @Override
    public List<CostumeDTO> getAllByRentStatus(boolean flag) {
        return costumeMapper.mapToCostumeDTOList(costumeUseCases.getAllByRentStatus(flag));
    }

    @Override
    public List<CostumeDTO> getAllCostumesByAge(String forWhom) {
        return costumeMapper.mapToCostumeDTOList(costumeUseCases.getAllCostumesByAge(forWhom));
    }

    @Override
    public List<CostumeDTO> getAllCostumesByParams(String age, String size) {
        return costumeMapper.mapToCostumeDTOList(costumeUseCases.getAllCostumesByParams(age, size));
    }

    @Override
    public List<CostumeDTO> searchAllCostumesByName(String name) {
        return costumeMapper.mapToCostumeDTOList(costumeUseCases.searchAllCostumesByName(name));
    }

    @Override
    public void removeCostume(UUID id) {
        costumeUseCases.removeCostume(id);
    }

    @Override
    public void updateCostume(UUID id, CostumeDTO costume) {
        costumeUseCases.updateCostume(id, costumeMapper.mapToCostume(costume));
    }
}
