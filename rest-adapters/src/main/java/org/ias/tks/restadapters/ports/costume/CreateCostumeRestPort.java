package org.ias.tks.restadapters.ports.costume;

import org.ias.tks.restadapters.dto.costume.CostumeDTO;

public interface CreateCostumeRestPort {
    void addCostume(CostumeDTO costume);
}
