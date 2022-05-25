package org.ias.tks.restadapters.adapters;

import org.ias.tks.appcore.domainmodel.exceptions.CostumeInUseException;
import org.ias.tks.appports.application.RentUseCases;
import org.ias.tks.restadapters.dto.rent.RentOutputDTO;
import org.ias.tks.restadapters.mappers.RentMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class RentRestAdapter {

    @Inject
    private RentMapper rentMapper;

    @Inject
    private RentUseCases rentUseCases;

    public void addRent(String login, List<UUID> costumeIds, String date) throws CostumeInUseException {
        rentUseCases.addRent(login, costumeIds, date);
    }

    public void add(RentOutputDTO rent) {
    }

    public List<RentOutputDTO> getCostumeAllocations(UUID id) {
        return rentMapper.mapToRentDTOList(rentUseCases.getCostumeAllocations(id));
    }

    public RentOutputDTO getRentById(UUID rentId) {
        return rentMapper.mapToRentDTO(rentUseCases.getRentById(rentId));
    }

    public List<RentOutputDTO> getAll() {
        return rentMapper.mapToRentDTOList(rentUseCases.getAll());
    }

    public List<RentOutputDTO> getRentsByCustomer(String login) {
        return rentMapper.mapToRentDTOList(rentUseCases.getRentsByCustomer(login));
    }

    public List<RentOutputDTO> userCurrentRents(String userLogin) {
        return rentMapper.mapToRentDTOList(rentUseCases.userCurrentRents(userLogin));
    }

    public List<RentOutputDTO> userPastRents(String userLogin) {
        return rentMapper.mapToRentDTOList(rentUseCases.userPastRents(userLogin));
    }

    public void endRent(String date, UUID rentId) {
        rentUseCases.endRent(date, rentId);
    }

}
