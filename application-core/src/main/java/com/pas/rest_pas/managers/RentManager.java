package com.pas.rest_pas.managers;

import com.pas.rest_pas.entities.user.User;
import com.pas.rest_pas.exceptions.*;
import com.pas.rest_pas.entities.costume.Costume;
import com.pas.rest_pas.repositories.RentRepository;
import com.pas.rest_pas.entities.Rent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class RentManager extends AbstractManager {

    private RentRepository rentRepository;
    private UserManager userManager;
    private CostumeManager costumeManager;


    @PostConstruct
    public void init() {
        User user = userManager.getUserByLogin("Radek460");
        Costume costume1 = costumeManager.getCostumeRepository().getAll().get(0);
        Costume costume2 = costumeManager.getCostumeRepository().getAll().get(1);

        List<UUID> costumeList = new ArrayList<>();
        costumeList.add(costume1.getId());
        costumeList.add(costume2.getId());

        try {
            addRent(user.getLogin(), costumeList, LocalDate.now().toString());
        } catch(CostumeInUseException e) {
            e.printStackTrace();
        }
    }

    @Inject
    public void setRentRepository(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    @Inject
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    @Inject
    public void setCostumeManager(CostumeManager costumeManager) {
        this.costumeManager = costumeManager;
    }

    public List<Rent> getRentsByCustomer(String login) {
        return rentRepository.getRentsByCustomer(login);
    }

//    public List<Rent> getRentsByDate(Predicate<Rent> predicate) {
//        return rentRepository.getRentsByDate(predicate);
//    }

    public List<Rent> getAll() {
        return rentRepository.getAll();
    }

    public List<Rent> getAllCurrent() {
        return rentRepository.getAll()
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

        while(id.hasNext()) {
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
            rentRepository.add(newRent);
        } else {
            LocalDate dateRented = LocalDate.parse(date);

            Rent newRent = new Rent(userManager.getUserByLogin(userLogin), costumes, totalPrice, dateRented);
            rentRepository.add(newRent);
        }
    }

    public Rent getRentById(UUID rentId) throws RentByIdNotFound {
        Rent tmpRent = rentRepository.getRentById(rentId);

        if(tmpRent == null) {
            throw new RentByIdNotFound();
        }
        return tmpRent;
    }

    public List<Rent> userCurrentRents(String userLogin) throws UserByLoginNotFound {
        if (userManager.getUserByLogin(userLogin) == null) {
            throw new UserByLoginNotFound();
        }
        return rentRepository.userCurrentRents(userLogin);
    }
    public List<Rent> userPastRents(String userLogin) throws UserByLoginNotFound {
        if (userManager.getUserByLogin(userLogin) == null) {
            throw new UserByLoginNotFound();
        }
        return rentRepository.userPastRents(userLogin);
    }

    public List<Rent> getCostumeAllocations(UUID id) {
        if (costumeManager.getCostumeById(id) == null) {
            throw new CostumeByIdNotFound();
        }
        return rentRepository.getCostumeAllocations(id);
    }

    public void endRent(String date, UUID rentId) throws RentByIdNotFound {
        rentRepository.endRent(date, rentId);
    }

    public void deleteRentFromRepo(UUID rentId) throws RentByIdNotFound{
        Rent rentToBeDeleted = rentRepository.getRentById(rentId);
        if (rentToBeDeleted == null) {
            throw new RentByIdNotFound();
        }
        rentRepository.setRentedCostumesToNotRented(rentId);
        rentRepository.delete(rentToBeDeleted);
    }

}
