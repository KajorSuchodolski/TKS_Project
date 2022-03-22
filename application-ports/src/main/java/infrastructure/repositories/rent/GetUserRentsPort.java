package infrastructure.repositories.rent;

import org.ias.tks.appcore.entities.Rent;

import java.util.List;

public interface GetUserRentsPort {
    List<Rent> getRentsByCustomer(String login);

    List<Rent> userCurrentRents(String userLogin);

    List<Rent> userPastRents(String userLogin);
}
