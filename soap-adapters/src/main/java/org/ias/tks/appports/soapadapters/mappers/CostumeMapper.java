package org.ias.tks.appports.soapadapters.mappers;

import org.ias.tks.appcore.domainmodel.model.costume.Costume;
import org.ias.tks.appcore.domainmodel.model.costume.CostumeSize;
import org.ias.tks.appcore.domainmodel.model.costume.ForWhom;
import org.ias.tks.appports.soapadapters.dto.costume.CostumeInputSOAP;
import org.ias.tks.appports.soapadapters.dto.costume.CostumeOutputSOAP;


import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//TODO needs to be refactored
@RequestScoped
public class CostumeMapper {

    public Costume mapToCostume(CostumeInputSOAP costumeSOAP){
        Costume costume = new Costume(
                costumeSOAP.getName(),
                CostumeSize.valueOf(costumeSOAP.getCostumeSize().toString()),
                ForWhom.valueOf(costumeSOAP.getForWhom().toString()),
                costumeSOAP.getPrice()
        );
        //temporary conditional
        if(costumeSOAP.getId() != null) {
            costume.setId(UUID.fromString(costumeSOAP.getId()));
        }
        return costume;
    }

    public Costume mapToCostume(CostumeOutputSOAP costumeSOAP){
        Costume costume = new Costume(
                costumeSOAP.getName(),
                CostumeSize.valueOf(costumeSOAP.getCostumeSize().toString()),
                ForWhom.valueOf(costumeSOAP.getForWhom().toString()),
                costumeSOAP.getPrice()
        );
        //temporary conditional
        if(costumeSOAP.getId() != null) {
            costume.setId(UUID.fromString(costumeSOAP.getId()));
        }
        return costume;
    }

    public CostumeOutputSOAP mapToCostumeOutputSOAP(Costume costume){
        CostumeOutputSOAP costumeOutputSOAP = new CostumeOutputSOAP(
                costume.getName(),
                (costume.getCostumeSize().toString()),
                (costume.getForWhom().toString()),
                costume.getPrice()
        );
        //temporary conditional
        if(costume.getId() != null) {
            costumeOutputSOAP.setId(costume.getId().toString());
        }
        return costumeOutputSOAP;
    }

    public List<Costume> mapToCostumeList(List<CostumeInputSOAP> listDTO) {
        List<Costume> list = new ArrayList<>();

        for (CostumeInputSOAP costumeEntity : listDTO) {
            list.add(mapToCostume(costumeEntity));
        }
        return list;
    }

    public List<CostumeOutputSOAP> mapToCostumeSOAPList(List<Costume> list) {
        List<CostumeOutputSOAP> listDTO = new ArrayList<>();
        for (Costume costume : list) {
            listDTO.add(mapToCostumeOutputSOAP(costume));
        }
        return listDTO;
    }



}
