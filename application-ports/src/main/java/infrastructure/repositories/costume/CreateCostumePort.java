package infrastructure.repositories.costume;

import org.ias.tks.appcore.entities.costume.Costume;

public interface CreateCostumePort {
    void addCostume(Costume costume);
}
