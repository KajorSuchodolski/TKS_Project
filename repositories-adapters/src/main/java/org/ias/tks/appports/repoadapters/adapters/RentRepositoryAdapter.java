package org.ias.tks.appports.repoadapters.adapters;

import org.ias.tks.appcore.domainmodel.model.rent.Rent;
import org.ias.tks.appports.infrastructure.rent.*;
import org.ias.tks.appports.repoadapters.mappers.RentMapper;
import org.ias.tks.appports.repoadapters.repositories.RentRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

public class RentRepositoryAdapter implements CreateRentPort, GetRentPort, GetUserRentsPort, GetCostumeRentsPort, UpdateRentPort {

    @Inject
    private RentMapper rentMapper;

    @Inject
    private RentRepository rentRepository;


    @Override
    public void addRent(Rent rent) {
        rentRepository.add(rentMapper.mapToRentEntity(rent));
    }

    @Override
    public List<Rent> getCostumeAllocations(UUID id) {
        return rentMapper.mapToRentList(rentRepository.getCostumeAllocations(id));
    }

    @Override
    public Rent getRentById(UUID rentId) {
        return rentMapper.mapToRent(rentRepository.getRentById(rentId));
    }

    @Override
    public List<Rent> getRentsByCustomer(String login) {
        return rentMapper.mapToRentList(rentRepository.getRentsByCustomer(login));
    }

    @Override
    public List<Rent> userCurrentRents(String userLogin) {
        return rentMapper.mapToRentList(rentRepository.userCurrentRents(userLogin));
    }

    @Override
    public List<Rent> userPastRents(String userLogin) {
        return rentMapper.mapToRentList(rentRepository.userPastRents(userLogin));
    }


    @Override
    public void endRent(String date, UUID rentId) {
        rentRepository.endRent(date, rentId);

    }

}
