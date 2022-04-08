package org.ias.tks.restadapters.mappers;

import org.ias.tks.appcore.domainmodel.model.rent.Rent;
import org.ias.tks.restadapters.dto.rent.RentInputDTO;
import org.ias.tks.restadapters.dto.rent.RentOutputDTO;

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

    public RentOutputDTO mapToRentDTO(Rent rent) {
        RentOutputDTO rentOutputDTO = new RentOutputDTO(
                userMapper.mapToUserOutputDTO(rent.getUser()),
                costumeMapper.mapToCostumeDTOList(rent.getCostumes()),
                rent.getPrice(),
                rent.getBeginTime()
        );

        rentOutputDTO.setId(rent.getId());
        return rentOutputDTO;
    }

    public Rent mapToRent(RentInputDTO rentInputDTO) {

        Rent rent = new Rent(
                userMapper.mapToUser(rentInputDTO.getUser()),
                costumeMapper.mapToCostumeList(rentInputDTO.getCostumes()),
                rentInputDTO.getPrice(),
                rentInputDTO.getBeginTime()
        );
        rent.setId(rentInputDTO.getId());
        return rent;
    }

    public List<Rent> mapToRentList(List<RentInputDTO> rentInputDTOS) {
        List<Rent> list = new ArrayList<>();

        for (RentInputDTO rentInputDTO : rentInputDTOS) {
            list.add(mapToRent(rentInputDTO));
        }
        return list;
    }

    public List<RentOutputDTO> mapToRentDTOList(List<Rent> list) {
        List<RentOutputDTO> listEntity = new ArrayList<>();
        for (Rent rent : list) {
            listEntity.add(mapToRentDTO(rent));
        }
        return listEntity;
    }

}
