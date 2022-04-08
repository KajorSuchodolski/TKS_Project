package org.ias.tks.restadapters.ports.rent;

import org.ias.tks.restadapters.dto.rent.RentOutputDTO;

import java.util.List;

public interface GetUserRentsRestPort {
    List<RentOutputDTO> getRentsByCustomer(String login);

    List<RentOutputDTO> userCurrentRents(String userLogin);

    List<RentOutputDTO> userPastRents(String userLogin);
}
