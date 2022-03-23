package org.ias.tks.restadapters.dto.rent;

import org.ias.tks.appcore.domainmodel.model.costume.Costume;
import org.ias.tks.appcore.domainmodel.model.user.User;
import org.ias.tks.restadapters.dto.ModelDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class RentDTO extends ModelDTO {

    private User user;
    private List<Costume> costumes;
    private double price;
    private LocalDate beginTime;
    private LocalDate endTime;

    public RentDTO() {

    }

    public RentDTO(User user, List<Costume> costumes, double price, LocalDate beginTime) {
        this.user = user;
        this.costumes = costumes;
        this.price = price;
        this.beginTime = beginTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Costume> getCostumes() {
        return costumes;
    }

    public void setCostumes(List<Costume> costumes) {
        this.costumes = costumes;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDate beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    @Override
    public UUID getId() {
        return super.getId();
    }


}
