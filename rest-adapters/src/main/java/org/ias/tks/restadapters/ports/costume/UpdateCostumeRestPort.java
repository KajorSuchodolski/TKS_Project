package org.ias.tks.restadapters.ports.costume;

import org.ias.tks.restadapters.dto.costume.CostumeDTO;

import java.util.UUID;

public interface UpdateCostumeRestPort {
    void updateCostume(UUID id, CostumeDTO costume);
}
