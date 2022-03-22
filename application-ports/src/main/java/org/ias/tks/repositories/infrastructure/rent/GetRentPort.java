package org.ias.tks.repositories.infrastructure.rent;

import org.ias.tks.model.rent.Rent;

import java.util.UUID;

public interface GetRentPort {
    Rent getRentById(UUID rentId);
}
