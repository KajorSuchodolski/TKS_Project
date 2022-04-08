package org.ias.tks.restadapters.ports.rent;


import org.ias.tks.restadapters.dto.rent.RentDTO;

import java.util.List;
import java.util.UUID;

public interface GetCostumeRentsRestPort {
    List<RentDTO> getCostumeAllocations(UUID id);

}
