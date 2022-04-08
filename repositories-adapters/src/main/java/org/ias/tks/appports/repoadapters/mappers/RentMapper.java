package org.ias.tks.appports.repoadapters.mappers;


import org.ias.tks.appcore.domainmodel.model.rent.Rent;
import org.ias.tks.appports.repoadapters.entities.rent.RentEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class RentMapper {

    @Inject
    private UserMapper userMapper;

    @Inject
    private CostumeMapper costumeMapper;

    public RentEntity mapToRentEntity(Rent rent) {
        RentEntity rentEntity = new RentEntity(
                userMapper.mapToUserEntity(rent.getUser()),
                costumeMapper.mapToCostumeEntityList(rent.getCostumes()),
                rent.getPrice(),
                rent.getBeginTime()
        );

        rentEntity.setId(rent.getId());
        return rentEntity;
    }

    public Rent mapToRent(RentEntity rentEntity) {

        Rent rent = new Rent(
                userMapper.mapToUser(rentEntity.getUser()),
                costumeMapper.mapToCostumeList(rentEntity.getCostumes()),
                rentEntity.getPrice(),
                rentEntity.getBeginTime()
        );
        rent.setId(rentEntity.getId());
        return rent;
    }

    public List<Rent> mapToRentList(List<RentEntity> listEntity) {
        List<Rent> list = new ArrayList<>();

        for (RentEntity rentEntity : listEntity) {
            list.add(mapToRent(rentEntity));
        }
        return list;
    }

    public List<RentEntity> mapToRentEntityList(List<Rent> list) {
        List<RentEntity> listEntity = new ArrayList<>();
        for (Rent rent : list) {
            listEntity.add(mapToRentEntity(rent));
        }
        return listEntity;
    }
}
