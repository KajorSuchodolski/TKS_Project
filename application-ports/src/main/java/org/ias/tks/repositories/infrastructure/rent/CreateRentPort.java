package org.ias.tks.repositories.infrastructure.rent;

import java.util.List;
import java.util.UUID;

public interface CreateRentPort {
    void addRent(String userLogin, List<UUID> costumeIds, String date);
}
