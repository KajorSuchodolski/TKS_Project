package org.ias.tks.restadapters.mappers;

import org.ias.tks.appcore.domainmodel.model.rent.Rent;
import org.ias.tks.restadapters.dto.rent.RentDTO;

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

    public RentDTO mapToRentDTO(Rent rent) {
        RentDTO rentDTO = new RentDTO(
                userMapper.mapToUserInputDTO(rent.getUser()),
                costumeMapper.mapToCostumeDTOList(rent.getCostumes()),
                rent.getPrice(),
                rent.getBeginTime()
        );

        rentDTO.setId(rent.getId());
        return rentDTO;
    }

    public Rent mapToRent(RentDTO rentDTO) {

        Rent rent = new Rent(
                userMapper.mapToUser(rentDTO.getUser()),
                costumeMapper.mapToCostumeList(rentDTO.getCostumes()),
                rentDTO.getPrice(),
                rentDTO.getBeginTime()
        );
        rent.setId(rentDTO.getId());
        return rent;
    }

    public List<Rent> mapToRentList(List<RentDTO> rentDTOS) {
        List<Rent> list = new ArrayList<>();

        for (RentDTO rentDTO : rentDTOS) {
            list.add(mapToRent(rentDTO));
        }
        return list;
    }

    public List<RentDTO> mapToRentDTOList(List<Rent> list) {
        List<RentDTO> listEntity = new ArrayList<>();
        for (Rent rent : list) {
            listEntity.add(mapToRentDTO(rent));
        }
        return listEntity;
    }

}
