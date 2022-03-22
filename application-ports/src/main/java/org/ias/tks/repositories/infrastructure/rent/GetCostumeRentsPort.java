package org.ias.tks.repositories.infrastructure.rent;

import org.ias.tks.model.rent.Rent;

import java.util.List;
import java.util.UUID;

public interface GetCostumeRentsPort {
    List<Rent> getCostumeAllocations(UUID id);
}
