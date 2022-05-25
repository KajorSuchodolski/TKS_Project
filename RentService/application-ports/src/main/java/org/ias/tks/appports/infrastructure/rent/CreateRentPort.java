package org.ias.tks.appports.infrastructure.rent;

import org.ias.tks.appcore.domainmodel.model.rent.Rent;

public interface CreateRentPort {
    void addRent(Rent rent);

    void add(Rent rent);
}
