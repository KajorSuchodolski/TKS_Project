package org.ias.tks.restadapters.ports.rent;

import org.ias.tks.appcore.domainmodel.exceptions.CostumeInUseException;
import org.ias.tks.restadapters.dto.rent.RentDTO;

import java.util.List;
import java.util.UUID;

public interface CreateRentRestPort {
    void addRent(String login, List<UUID> costumeIds, String date) throws CostumeInUseException;

    void add(RentDTO rent);
}
