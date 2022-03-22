package org.ias.tks.repositories.infrastructure.costume;

import org.ias.tks.model.costume.Costume;

import java.util.UUID;

public interface UpdateCostumePort {
    void updateCostume(UUID id, Costume costume);

    void activateRent(UUID id);

    void deactivateRent(UUID id);
}
