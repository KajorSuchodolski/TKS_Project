package org.ias.tks.appports.repoadapters.mappers;


import org.ias.tks.appcore.domainmodel.model.rent.Rent;
import org.ias.tks.appports.repoadapters.entities.rent.RentEntity;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class RentMapper {

    @Inject
    private UserMapper userMapper;

    @Inject
    private

    public RentEntity mapToRentEntity(Rent rent) {
        return new RentEntity(
                userMapper.mapToUserEntity(rent.getUser()),
                das,
                rent.getPrice(),
                rent.getBeginTime()
        );
    }

    public Rent mapToRent(RentEntity rent) {

        return new Rent(
                userMapper.mapToUserEntity(rent.getUser()),
                das,
                rent.getPrice(),
                rent.getBeginTime()
        );
    }

    public List<Rent> mapToRentList(List<RentEntity> listEntity) {
        List<Rent> list = new ArrayList<>();

        for (RentEntity rentEntity : listEntity) {
            list.add(mapToUser(rentEntity));
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
