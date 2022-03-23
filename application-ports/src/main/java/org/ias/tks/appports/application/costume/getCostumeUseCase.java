package org.ias.tks.appports.application.costume;

import org.ias.tks.appcore.domainmodel.exceptions.EntityValidationException;
import org.ias.tks.appcore.domainmodel.model.costume.Costume;

import java.util.List;
import java.util.UUID;

public interface getCostumeUseCase {
    Costume getCostumeById(UUID id);
    List<Costume> getAll();
    List<Costume> getAllByRentStatus(boolean flag);
    List<Costume> getAllCostumesByAge(String forWhom);
    List<Costume> searchAllCostumesByName(String name);
    List<Costume> getAllCostumesByParams(String age, String size) throws EntityValidationException;
    
}
