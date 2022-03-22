package org.ias.tks.appports.repoadapters.repositories;


import org.ias.tks.appports.repoadapters.entities.costume.CostumeEntity;
import org.ias.tks.appports.repoadapters.entities.rent.RentEntity;
import org.ias.tks.appports.repoadapters.domain_model.exceptions.EndRentBeforeBeginException;
import org.ias.tks.appports.repoadapters.domain_model.exceptions.RentByIdNotFound;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@ApplicationScoped
public class RentRepository extends AbstractRepository<RentEntity> {

    public RentRepository() {
    }

    public List<RentEntity> getRentsByCustomer(String login) {
        return getAll()
                .stream()
                .filter(e -> Objects.equals(e.getUser().getLogin(), login))
                .collect(Collectors.toList());
    }

    public RentEntity getRentById(UUID rentId) {
        return getAll()
                .stream()
                .filter(e -> e.getId().compareTo(rentId) == 0)
                .findAny()
                .orElse(null);
    }

    public void endRent(String date, UUID rentId) {
        if(getRentById(rentId) == null) {
            throw new RentByIdNotFound();
        } else if (Objects.equals(date, "now")) {
            LocalDate dateRentEnded = LocalDate.now();
            getRentById(rentId).setEndTime(dateRentEnded);
            for(CostumeEntity costume : getRentById(rentId).getCostumes()) {
                costume.setRented(true);
            }
        } else {
            LocalDate dateRentEnded = LocalDate.parse(date);
            System.out.println(dateRentEnded);
            if(dateRentEnded.isBefore(getRentById(rentId).getBeginTime())) {
                throw new EndRentBeforeBeginException();
            }
            getRentById(rentId).setEndTime(dateRentEnded);
        }
    }

    public void setRentedCostumesToNotRented(UUID rentId) {

            Iterator<CostumeEntity> it = getRentById(rentId).getCostumes().iterator();
            while(it.hasNext()) {
                it.next().setRented(false);
            }
    }

    public List<RentEntity> userCurrentRents(String userLogin) {
        Predicate<RentEntity> byLogin = rent -> rent.getUser().getLogin().equals(userLogin);

        return getAll()
                .stream()
                .filter(byLogin)
                .filter(e -> e.getEndTime() == null)
                .collect(Collectors.toList());
    }

    public List<RentEntity> userPastRents(String userLogin) {
        Predicate<RentEntity> byLogin = rent -> rent.getUser().getLogin().equals(userLogin);

        return getAll()
                .stream()
                .filter(byLogin)
                .filter(e -> e.getEndTime() != null)
                .collect(Collectors.toList());
    }

    public List<RentEntity> getCostumeAllocations(UUID id) {

//        Predicate<Rent> byUUID = rent -> rent.getCostumes().stream().filter(e -> e.getId().equals(id));

        return getAll()
                .stream()
                .filter(e -> e.getCostumes().stream().anyMatch(c -> c.getId().equals(id)))
                .collect(Collectors.toList());
    }
}
