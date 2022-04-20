package org.ias.tks.appports.soapadapters.adapters;

import org.ias.tks.appcore.domainmodel.exceptions.CostumeInUseException;
import org.ias.tks.appports.application.RentUseCases;
import org.ias.tks.appports.soapadapters.dto.rent.RentOutputSOAP;
import org.ias.tks.appports.soapadapters.mappers.RentMapper;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class RentSOAPAdapter {

    @Inject
    private RentMapper rentMapper;

    @Inject
    private RentUseCases rentUseCases;

    public void addRent(String login, List<UUID> costumeIds, String date) throws CostumeInUseException {
        rentUseCases.addRent(login, costumeIds, date);
    }

    public void add(RentOutputSOAP rent) {
    }

    public List<RentOutputSOAP> getCostumeAllocations(UUID id) {
        return rentMapper.mapToRentSOAPList(rentUseCases.getCostumeAllocations(id));
    }

    public RentOutputSOAP getRentById(UUID rentId) {
        return rentMapper.mapToRentSOAP(rentUseCases.getRentById(rentId));
    }

    public List<RentOutputSOAP> getAll() {
        return rentMapper.mapToRentSOAPList(rentUseCases.getAll());
    }

    public List<RentOutputSOAP> getRentsByCustomer(String login) {
        return rentMapper.mapToRentSOAPList(rentUseCases.getRentsByCustomer(login));
    }

    public List<RentOutputSOAP> userCurrentRents(String userLogin) {
        return rentMapper.mapToRentSOAPList(rentUseCases.userCurrentRents(userLogin));
    }

    public List<RentOutputSOAP> userPastRents(String userLogin) {
        return rentMapper.mapToRentSOAPList(rentUseCases.userPastRents(userLogin));
    }

    public void endRent(String date, UUID rentId) {
        rentUseCases.endRent(date, rentId);
    }

}
