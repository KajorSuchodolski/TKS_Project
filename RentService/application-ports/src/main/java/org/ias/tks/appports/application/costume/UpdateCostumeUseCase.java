package org.ias.tks.appports.application.costume;

import org.ias.tks.appcore.domainmodel.model.costume.Costume;

import java.util.UUID;

public interface UpdateCostumeUseCase {

    void updateCostume(UUID id, Costume costume);
}
