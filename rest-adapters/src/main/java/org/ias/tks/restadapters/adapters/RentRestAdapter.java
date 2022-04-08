package org.ias.tks.restadapters.adapters;

import org.ias.tks.appcore.domainmodel.exceptions.CostumeInUseException;
import org.ias.tks.appports.application.RentUseCases;
import org.ias.tks.restadapters.dto.rent.RentDTO;
import org.ias.tks.restadapters.mappers.RentMapper;
import org.ias.tks.restadapters.ports.RentRestPorts;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class RentRestAdapter implements RentRestPorts {

    @Inject
    private RentMapper rentMapper;

    @Inject
    private RentUseCases rentUseCases;

    @Override
    public void addRent(String login, List<UUID> costumeIds, String date) throws CostumeInUseException {
        rentUseCases.addRent(login, costumeIds, date);
    }

    @Override
    public void add(RentDTO rent) {
    }

    @Override
    public List<RentDTO> getCostumeAllocations(UUID id) {
        return rentMapper.mapToRentDTOList(rentUseCases.getCostumeAllocations(id));
    }

    @Override
    public RentDTO getRentById(UUID rentId) {
        return rentMapper.mapToRentDTO(rentUseCases.getRentById(rentId));
    }

    @Override
    public List<RentDTO> getAll() {
        return rentMapper.mapToRentDTOList(rentUseCases.getAll());
    }

    @Override
    public List<RentDTO> getRentsByCustomer(String login) {
        return rentMapper.mapToRentDTOList(rentUseCases.getRentsByCustomer(login));
    }

    @Override
    public List<RentDTO> userCurrentRents(String userLogin) {
        return rentMapper.mapToRentDTOList(rentUseCases.userCurrentRents(userLogin));
    }

    @Override
    public List<RentDTO> userPastRents(String userLogin) {
        return rentMapper.mapToRentDTOList(rentUseCases.userPastRents(userLogin));
    }


    @Override
    public void endRent(String date, UUID rentId) {
        rentUseCases.endRent(date, rentId);
    }

}
