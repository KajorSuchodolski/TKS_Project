package org.ias.tks.appports.infrastructure.costume;

import org.ias.tks.appcore.domainmodel.model.costume.Costume;

import java.util.List;
import java.util.UUID;

public interface GetCostumePort {
    Costume getCostumeById(UUID id);

    List<Costume> getAll();

    List<Costume> getAllByRentStatus(boolean flag);

    List<Costume> getAllCostumesByAge(String forWhom);

    List<Costume> getAllCostumesByParams(String age, String size);

    List<Costume> searchAllCostumesByName(String name);
}
