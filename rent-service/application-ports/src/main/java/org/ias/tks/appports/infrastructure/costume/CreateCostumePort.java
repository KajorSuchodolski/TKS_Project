package org.ias.tks.appports.infrastructure.costume;

import org.ias.tks.appcore.domainmodel.model.costume.Costume;

public interface CreateCostumePort {
    void addCostume(Costume costume);
}
