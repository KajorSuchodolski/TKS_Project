package org.ias.tks.appports.application.rent;

import org.ias.tks.appcore.domainmodel.exceptions.CostumeInUseException;
import org.ias.tks.appcore.domainmodel.model.rent.Rent;

import java.util.List;
import java.util.UUID;

public interface CreateRentUseCase {
    void addRent(String userLogin, List<UUID> costumeIds, String date) throws CostumeInUseException;
}
