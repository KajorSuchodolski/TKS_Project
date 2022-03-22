package infrastructure.repositories.rent;

import org.ias.tks.appcore.entities.Rent;

import java.util.List;
import java.util.UUID;

public interface GetRentPort {
    Rent getRentById(UUID rentId);
}
