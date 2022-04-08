package org.ias.tks.restadapters.mappers;

import org.ias.tks.appcore.domainmodel.model.costume.Costume;
import org.ias.tks.restadapters.dto.costume.CostumeDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

//TODO needs to be refactored
@ApplicationScoped
public class CostumeMapper {

    public Costume mapToCostume(CostumeDTO costumeDTO){
        Costume costume = new Costume(
                costumeDTO.getName(),
                costumeDTO.getCostumeSize(),
                costumeDTO.getForWhom(),
                costumeDTO.getPrice()
        );
        //temporary conditional
        if(costumeDTO.getId() != null) {
            costume.setId(costumeDTO.getId());
        }
        return costume;
    }

    public CostumeDTO mapToCostumeDTO(Costume costume){
        CostumeDTO costumeDTO = new CostumeDTO(
                costume.getName(),
                costume.getCostumeSize(),
                costume.getForWhom(),
                costume.getPrice()
        );
        //temporary conditional
        if(costume.getId() != null) {
            costume.setId(costume.getId());
        }
        return costumeDTO;
    }

    public List<Costume> mapToCostumeList(List<CostumeDTO> listDTO) {
        List<Costume> list = new ArrayList<>();

        for (CostumeDTO costumeEntity : listDTO) {
            list.add(mapToCostume(costumeEntity));
        }
        return list;
    }

    public List<CostumeDTO> mapToCostumeDTOList(List<Costume> list) {
        List<CostumeDTO> listDTO = new ArrayList<>();
        for (Costume costume : list) {
            listDTO.add(mapToCostumeDTO(costume));
        }
        return listDTO;
    }



}
