package org.ias.tks.restadapters.adapters;

import org.ias.tks.appcore.domainmodel.exceptions.CostumeInUseException;
import org.ias.tks.appcore.domainmodel.model.rent.Rent;
import org.ias.tks.appports.application.RentUseCases;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@RequestScoped
public class RentRestAdapter implements RentUseCases {

    @Override
    public void addRent(String userLogin, List<UUID> costumeIds, String date) throws CostumeInUseException {

    }

    @Override
    public void endRent(String date, UUID rentId) {

    }

    @Override
    public List<Rent> getCostumeAllocations(UUID id) {
        return null;
    }

    @Override
    public Rent getRentById(UUID rentId) {
        return null;
    }

    @Override
    public List<Rent> getAll() {
        return null;
    }

    @Override
    public List<Rent> getRentsByCustomer(String login) {
        return null;
    }

    @Override
    public List<Rent> userCurrentRents(String userLogin) {
        return null;
    }

    @Override
    public List<Rent> userPastRents(String userLogin) {
        return null;
    }

    @Override
    public List<Rent> getAllCurrent() {
        return null;
    }

    @Override
    public void removeRent(UUID id) {

    }
}
