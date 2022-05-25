package org.ias.tks.appports.infrastructure.rent;

import org.ias.tks.appcore.domainmodel.model.rent.Rent;

import java.util.List;
import java.util.UUID;

public interface GetRentPort {
    Rent getRentById(UUID rentId);

    List<Rent> getAll();
}
