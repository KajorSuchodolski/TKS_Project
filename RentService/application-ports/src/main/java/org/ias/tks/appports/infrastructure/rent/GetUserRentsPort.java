package org.ias.tks.appports.infrastructure.rent;

import org.ias.tks.appcore.domainmodel.model.rent.Rent;

import java.util.List;

public interface GetUserRentsPort {
    List<Rent> getRentsByCustomer(String login);

    List<Rent> userCurrentRents(String userLogin);

    List<Rent> userPastRents(String userLogin);
}
