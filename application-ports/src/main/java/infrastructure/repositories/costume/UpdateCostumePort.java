package infrastructure.repositories.costume;

import org.ias.tks.appcore.entities.costume.Costume;

import java.util.UUID;

public interface UpdateCostumePort {
    void updateCostume(UUID id, Costume costume);

    void activateRent(UUID id);

    void deactivateRent(UUID id);
}
