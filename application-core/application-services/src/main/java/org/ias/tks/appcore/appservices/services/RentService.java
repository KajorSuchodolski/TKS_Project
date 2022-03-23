package org.ias.tks.appcore.appservices.services;

import org.ias.tks.appcore.domainmodel.exceptions.*;
import org.ias.tks.appcore.domainmodel.model.costume.Costume;
import org.ias.tks.appcore.domainmodel.model.rent.Rent;
import org.ias.tks.appcore.domainmodel.model.user.User;
import org.ias.tks.appports.infrastructure.rent.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class RentService extends AbstractService {

    @Inject
    private UserService userManager;

    @Inject
    private CostumeService costumeManager;

    @Inject
    private CreateRentPort createRentPort;

    @Inject
    private GetCostumeRentsPort getCostumeRentsPort;

    @Inject
    private GetRentPort getRentPort;

    @Inject
    private GetUserRentsPort getUserRentsPort;

    @Inject
    private UpdateRentPort updateRentPort;

    @Inject
    private RemoveRentPort removeRentPort;

    @PostConstruct
    public void init() {
        User user = userManager.getUserByLogin("Radek460");
        Costume costume1 = costumeManager.getAll().get(0);
        Costume costume2 = costumeManager.getAll().get(1);

        List<UUID> costumeList = new ArrayList<>();
        costumeList.add(costume1.getId());
        costumeList.add(costume2.getId());

        try {
            addRent(user.getLogin(), costumeList, LocalDate.now().toString());
        } catch (CostumeInUseException e) {
            e.printStackTrace();
        }
    }


    public List<Rent> getRentsByCustomer(String login) {
        return getUserRentsPort.getRentsByCustomer(login);
    }

//    public List<Rent> getRentsByDate(Predicate<Rent> predicate) {
//        return rentRepository.getRentsByDate(predicate);
//    }

    public List<Rent> getAll() {
        return getRentPort.getAll();
    }

    public List<Rent> getAllCurrent() {
        return getRentPort.getAll()
                .stream()
                .filter(e -> e.getEndTime() == null)
                .collect(Collectors.toList());
    }

    public void addRent(String userLogin, List<UUID> costumeIds, String date) throws UserByLoginNotFound, CostumeInUseException, CostumeByIdNotFound, DateInPastException, WrongDateFormatException {
        if (userManager.getUserByLogin(userLogin) == null) {
            throw new UserByLoginNotFound();
        }
        if (!userManager.getUserByLogin(userLogin).isActive()) {
            throw new UserInactiveException();
        }
        if (!Objects.equals(date, "now")) {
            try {
                LocalDate.parse(date);
            } catch (DateTimeException e) {
                throw new WrongDateFormatException();
            }

            LocalDate dateRented = LocalDate.parse(date);

            if (dateRented.isBefore(LocalDate.now())) {
                throw new DateInPastException();
            }
        }

        List<Costume> costumes = new ArrayList<>();
        Iterator<UUID> id = costumeIds.iterator();

        double totalPrice = 0;

        while (id.hasNext()) {
            Costume currentCostume = costumeManager.getCostumeById(id.next());
            if (currentCostume == null) {
                throw new CostumeByIdNotFound();
            }
            if (currentCostume.isRented()) {
                throw new CostumeInUseException();
            }
            currentCostume.setRented(true);
            costumes.add(currentCostume);
            totalPrice += currentCostume.getPrice();
        }

        if (Objects.equals(date, "now")) {
            LocalDate dateRented = LocalDate.now();
            Rent newRent = new Rent(userManager.getUserByLogin(userLogin), costumes, totalPrice, dateRented);
            createRentPort.add(newRent);
        } else {
            LocalDate dateRented = LocalDate.parse(date);

            Rent newRent = new Rent(userManager.getUserByLogin(userLogin), costumes, totalPrice, dateRented);
            createRentPort.add(newRent);
        }
    }

    public Rent getRentById(UUID rentId) throws RentByIdNotFound {
        Rent tmpRent = getRentPort.getRentById(rentId);

        if (tmpRent == null) {
            throw new RentByIdNotFound();
        }
        return tmpRent;
    }

    public List<Rent> userCurrentRents(String userLogin) throws UserByLoginNotFound {
        if (userManager.getUserByLogin(userLogin) == null) {
            throw new UserByLoginNotFound();
        }
        return getUserRentsPort.userCurrentRents(userLogin);
    }

    public List<Rent> userPastRents(String userLogin) throws UserByLoginNotFound {
        if (userManager.getUserByLogin(userLogin) == null) {
            throw new UserByLoginNotFound();
        }
        return getUserRentsPort.userPastRents(userLogin);
    }

    public List<Rent> getCostumeAllocations(UUID id) {
        if (costumeManager.getCostumeById(id) == null) {
            throw new CostumeByIdNotFound();
        }
        return getCostumeRentsPort.getCostumeAllocations(id);
    }

    public void endRent(String date, UUID rentId) throws RentByIdNotFound {
        updateRentPort.endRent(date, rentId);
    }

    public void removeRent(UUID rentId) throws RentByIdNotFound {
        Rent rentToBeDeleted = getRentPort.getRentById(rentId);
        if (rentToBeDeleted == null) {
            throw new RentByIdNotFound();
        }
        updateRentPort.setRentedCostumesToNotRented(rentId);
        removeRentPort.delete(rentToBeDeleted);
    }

}
