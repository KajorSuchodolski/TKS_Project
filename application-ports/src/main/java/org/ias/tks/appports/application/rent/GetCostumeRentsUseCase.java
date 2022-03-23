package org.ias.tks.appports.application.rent;

import org.ias.tks.appcore.domainmodel.model.rent.Rent;

import java.util.List;
import java.util.UUID;

public interface GetCostumeRentsUseCase {
    List<Rent> getCostumeAllocations(UUID id);

}
