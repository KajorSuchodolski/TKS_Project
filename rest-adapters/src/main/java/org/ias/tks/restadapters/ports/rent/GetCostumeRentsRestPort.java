package org.ias.tks.restadapters.ports.rent;


import org.ias.tks.restadapters.dto.rent.RentOutputDTO;

import java.util.List;
import java.util.UUID;

public interface GetCostumeRentsRestPort {
    List<RentOutputDTO> getCostumeAllocations(UUID id);

}
