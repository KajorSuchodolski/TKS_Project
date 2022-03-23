package org.ias.tks.appports.repoadapters.adapters;

import org.ias.tks.appcore.domainmodel.model.costume.Costume;
import org.ias.tks.appports.infrastructure.costume.CreateCostumePort;
import org.ias.tks.appports.infrastructure.costume.GetCostumePort;
import org.ias.tks.appports.infrastructure.costume.RemoveCostumePort;
import org.ias.tks.appports.infrastructure.costume.UpdateCostumePort;
import org.ias.tks.appports.repoadapters.entities.costume.CostumeSizeEnt;
import org.ias.tks.appports.repoadapters.entities.costume.ForWhomEnt;
import org.ias.tks.appports.repoadapters.exceptions.CostumeInUseException;
import org.ias.tks.appports.repoadapters.mappers.CostumeMapper;
import org.ias.tks.appports.repoadapters.repositories.CostumeRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CostumeRepositoryAdapter implements CreateCostumePort, GetCostumePort, RemoveCostumePort, UpdateCostumePort {

    @Inject
    private CostumeRepository costumeRepository;

    @Inject
    private CostumeMapper costumeMapper;

    @Override
    public void addCostume(Costume costume) {
        costumeRepository.addCostume(costumeMapper.mapToCostumeEntity(costume));
    }

    @Override
    public Costume getCostumeById(UUID id) {
        return costumeMapper.mapToCostume(costumeRepository.getCostumeById(id));
    }

    @Override
    public List<Costume> getAll() {
        return costumeMapper.mapToCostumeList(costumeRepository.getAll());
    }

    @Override
    public List<Costume> getAllByRentStatus(boolean flag) {
        return costumeMapper.mapToCostumeList(costumeRepository.getAllByRentStatus(flag));
    }

    @Override
    public List<Costume> getAllCostumesByAge(String forWhom) {
        return costumeMapper.mapToCostumeList(costumeRepository.getAllCostumesByAge(ForWhomEnt.valueOf(forWhom)));
    }

    @Override
    public List<Costume> getAllCostumesByParams(String age, String size) {
        return costumeMapper.mapToCostumeList(costumeRepository.getAllCostumesByParams(ForWhomEnt.valueOf(age), CostumeSizeEnt.valueOf(size)));
    }

    @Override
    public List<Costume> searchAllCostumesByName(String name) {
        return costumeMapper.mapToCostumeList(costumeRepository.searchCostumesByName(name));
    }

    @Override
    public void removeCostume(UUID id) {
        try {
            costumeRepository.removeCostume(id);
        } catch (CostumeInUseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCostume(UUID id, Costume costume) {
       costumeRepository.updateCostume(id, costumeMapper.mapToCostumeEntity(costume));
    }


}
