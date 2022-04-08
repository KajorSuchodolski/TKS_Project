package org.ias.tks.restadapters.ports.costume;

import org.ias.tks.restadapters.dto.costume.CostumeDTO;

import java.util.List;
import java.util.UUID;

public interface GetCostumeRestPort {
    CostumeDTO getCostumeById(UUID id);

    List<CostumeDTO> getAll();

    List<CostumeDTO> getAllByRentStatus(boolean flag);

    List<CostumeDTO> getAllCostumesByAge(String forWhom);

    List<CostumeDTO> getAllCostumesByParams(String age, String size);

    List<CostumeDTO> searchAllCostumesByName(String name);
}
