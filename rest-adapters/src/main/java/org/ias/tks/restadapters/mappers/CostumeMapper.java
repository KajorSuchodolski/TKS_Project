package org.ias.tks.restadapters.mappers;

import org.ias.tks.appcore.domainmodel.model.costume.Costume;
import org.ias.tks.restadapters.dto.CostumeDto;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CostumeMapper {

//    CostumeDto mapToCostumeDto(Costume costume){
//
//    }

    public Costume mapToCostume(CostumeDto costumeDto){
        return new Costume(costumeDto.getName(),costumeDto.getCostumeSize(),costumeDto.getForWhom(),costumeDto.getPrice());
    }

}
