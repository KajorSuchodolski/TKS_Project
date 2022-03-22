package org.ias.tks.appports.infrastructure.costume;

import org.ias.tks.appcore.domainmodel.model.costume.Costume;

import java.util.UUID;

public interface UpdateCostumePort {
    void updateCostume(UUID id, Costume costume);
}
