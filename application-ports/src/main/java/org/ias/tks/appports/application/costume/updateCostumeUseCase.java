package org.ias.tks.appports.application.costume;

import org.ias.tks.appcore.domainmodel.model.costume.Costume;

import java.util.UUID;

public interface updateCostumeUseCase {

    void updateCostume(UUID id, Costume costume);
}
