package org.ias.tks.restadapters.ports.rent;

import org.ias.tks.restadapters.dto.rent.RentOutputDTO;

import java.util.List;
import java.util.UUID;

public interface GetRentRestPort {
    RentOutputDTO getRentById(UUID rentId);

    List<RentOutputDTO> getAll();
}
