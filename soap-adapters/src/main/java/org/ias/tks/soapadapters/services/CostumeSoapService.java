package org.ias.tks.soapadapters.services;

import org.ias.tks.soapadapters.adapters.CostumeSOAPAdapter;

import org.ias.tks.soapadapters.dto.costume.CostumeInputSOAP;
import org.ias.tks.soapadapters.dto.costume.CostumeOutputSOAP;

import javax.inject.Inject;
import javax.jws.WebService;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;


@WebService(serviceName = "costumesoapapi")
public class CostumeSoapService {

    @Inject
    CostumeSOAPAdapter costumeSOAPAdapter;

    public String ping(){
        return "pong";
    }

    public List<CostumeOutputSOAP> getAll(){
        return costumeSOAPAdapter.getAllToSOAP();
    }

    public List<CostumeOutputSOAP> getAllRented() {
        return costumeSOAPAdapter.getAllByRentStatus(true);
    }

    public List<CostumeOutputSOAP> getAllAvailable() {
        return costumeSOAPAdapter.getAllByRentStatus(false);
    }

    public List<CostumeOutputSOAP> getAllCostumesByAge(@NotNull String age) {
        try {
            return costumeSOAPAdapter.getAllCostumesByAge(age);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public CostumeOutputSOAP getCostumeById(@NotNull String uuid) {
        try {
            return costumeSOAPAdapter.getCostumeById(UUID.fromString(uuid));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public CostumeInputSOAP addCostume(@NotNull CostumeInputSOAP costumeSOAP) {
        try {
            costumeSOAPAdapter.addCostume(costumeSOAP);
            return costumeSOAP;//XD
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public CostumeOutputSOAP updateCostume(@NotNull String id, @NotNull CostumeInputSOAP costumeDto) {
        try {
            costumeSOAPAdapter.updateCostume(UUID.fromString(id), costumeDto);
            return costumeSOAPAdapter.getCostumeById(UUID.fromString(id));

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public boolean removeCostume(String id) {
        try {
            costumeSOAPAdapter.removeCostume(UUID.fromString(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
