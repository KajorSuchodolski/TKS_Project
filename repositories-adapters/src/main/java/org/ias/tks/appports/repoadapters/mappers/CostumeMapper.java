package org.ias.tks.appports.repoadapters.mappers;

import org.ias.tks.appcore.domainmodel.model.costume.Costume;
import org.ias.tks.appcore.domainmodel.model.costume.CostumeSize;
import org.ias.tks.appcore.domainmodel.model.costume.ForWhom;
import org.ias.tks.appports.repoadapters.entities.costume.CostumeEntity;
import org.ias.tks.appports.repoadapters.entities.costume.CostumeSizeEnt;
import org.ias.tks.appports.repoadapters.entities.costume.ForWhomEnt;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class CostumeMapper {

    public CostumeEntity mapToCostumeEntity(Costume costume) {

        CostumeEntity costumeEntity = new CostumeEntity(
                costume.getName(),
                CostumeSizeEnt.valueOf(costume.getCostumeSize().toString()),
                ForWhomEnt.valueOf(costume.getForWhom().toString()),
                costume.getPrice());

        costumeEntity.setId(costume.getId());
        return costumeEntity;
    }

    public Costume mapToCostume(CostumeEntity costumeEntity) {
        Costume costume = new Costume(
                costumeEntity.getName(),
                CostumeSize.valueOf(costumeEntity.getCostumeSize().toString()),
                ForWhom.valueOf(costumeEntity.getForWhom().toString()),
                costumeEntity.getPrice());

        costume.setId(costumeEntity.getId());
        return costume;
    }


    public List<Costume> mapToCostumeList(List<CostumeEntity> listEntity) {
        List<Costume> list = new ArrayList<>();

        for (CostumeEntity costumeEntity : listEntity) {
            list.add(mapToCostume(costumeEntity));
        }
        return list;
    }

    public List<CostumeEntity> mapToCostumeEntityList(List<Costume> list) {
        List<CostumeEntity> listEntity = new ArrayList<>();
        for (Costume costume : list) {
            listEntity.add(mapToCostumeEntity(costume));
        }
        return listEntity;
    }

}


