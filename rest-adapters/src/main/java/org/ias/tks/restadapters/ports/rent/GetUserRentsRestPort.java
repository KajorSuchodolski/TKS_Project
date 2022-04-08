package org.ias.tks.restadapters.ports.rent;

import org.ias.tks.restadapters.dto.rent.RentDTO;

import java.util.List;

public interface GetUserRentsRestPort {
    List<RentDTO> getRentsByCustomer(String login);

    List<RentDTO> userCurrentRents(String userLogin);

    List<RentDTO> userPastRents(String userLogin);
}
