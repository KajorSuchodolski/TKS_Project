package org.ias.tks.repoadapters.entities;

import org.ias.tks.repoadapters.entities.costume.CostumeEnt;
import org.ias.tks.repoadapters.entities.user.UserEnt;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class RentEnt extends EntityEnt {

    private UserEnt user;
    private List<CostumeEnt> costumes;
    private double price;
    private LocalDate beginTime;
    private LocalDate endTime;


    public RentEnt() {

    }

    public RentEnt(UserEnt user, List<CostumeEnt> costumes, double price, LocalDate beginTime) {
        this.user = user;
        this.costumes = costumes;
        this.price = price;
        this.beginTime = beginTime;
    }

    public UserEnt getUser() {
        return user;
    }

    public void setUser(UserEnt user) {
        this.user = user;
    }

    public List<CostumeEnt> getCostumes() {
        return costumes;
    }

    public void setCostumes(List<CostumeEnt> costumes) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RentEnt rent)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(rent.price, price) == 0 && Objects.equals(user, rent.user) && Objects.equals(costumes, rent.costumes) && Objects.equals(beginTime, rent.beginTime) && Objects.equals(endTime, rent.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, costumes, price, beginTime, endTime);
    }

    @Override
    public String toString() {
        return "RentEnt{" +
                "user=" + user +
                ", costumes=" + costumes +
                ", price=" + price +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                '}';
    }
}