package org.ias.tks.appports.infrastructure.rent;

import org.ias.tks.appcore.domainmodel.model.rent.Rent;

public interface RemoveRentPort {
    void delete(Rent rent);
}
