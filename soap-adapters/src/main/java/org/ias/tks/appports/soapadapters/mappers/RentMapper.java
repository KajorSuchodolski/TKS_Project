package org.ias.tks.appports.soapadapters.mappers;

import org.ias.tks.appcore.domainmodel.model.costume.Costume;
import org.ias.tks.appcore.domainmodel.model.rent.Rent;
import org.ias.tks.appcore.domainmodel.model.user.User;
import org.ias.tks.appports.soapadapters.adapters.CostumeSOAPAdapter;
import org.ias.tks.appports.soapadapters.adapters.UserSOAPAdapter;
import org.ias.tks.appports.soapadapters.dto.costume.CostumeOutputSOAP;
import org.ias.tks.appports.soapadapters.dto.rent.RentInputSOAP;
import org.ias.tks.appports.soapadapters.dto.rent.RentOutputSOAP;
import org.ias.tks.appports.soapadapters.dto.user.UserInputSOAP;
import org.ias.tks.appports.soapadapters.dto.user.UserOutputSOAP;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class RentMapper {

    @Inject
    private UserMapper userMapper;

    @Inject
    private CostumeMapper costumeMapper;


    /// ?????????????????? ///
    @Inject
    private UserSOAPAdapter userSOAPAdapter;

    @Inject
    private CostumeSOAPAdapter costumeSOAPAdapter;


    public RentOutputSOAP mapToRentSOAP(Rent rent) {
        RentOutputSOAP rentOutputSOAP = new RentOutputSOAP(
                userMapper.mapToUserOutputSOAP(rent.getUser()),
                costumeMapper.mapToCostumeSOAPList(rent.getCostumes()),
                rent.getPrice(),
                rent.getBeginTime(),
                rent.getEndTime()
        );

        rentOutputSOAP.setId(rent.getId().toString());
        return rentOutputSOAP;
    }

    public List<RentOutputSOAP> mapToRentSOAPList(List<Rent> list) {
        List<RentOutputSOAP> listEntity = new ArrayList<>();
        for (Rent rent : list) {
            listEntity.add(mapToRentSOAP(rent));
        }
        return listEntity;
    }

}
