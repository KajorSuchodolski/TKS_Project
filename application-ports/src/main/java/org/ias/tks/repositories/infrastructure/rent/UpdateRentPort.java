package org.ias.tks.repositories.infrastructure.rent;

import java.util.UUID;

public interface UpdateRentPort {
    void endRent(String date, UUID rentId);

    void setRentedCostumesToNotRented(UUID rentId);
}