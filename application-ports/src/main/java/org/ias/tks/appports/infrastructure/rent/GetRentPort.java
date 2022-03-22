package org.ias.tks.appports.infrastructure.rent;

import org.ias.tks.appcore.domainmodel.model.rent.Rent;

import java.util.UUID;

public interface GetRentPort {
    Rent getRentById(UUID rentId);
}
