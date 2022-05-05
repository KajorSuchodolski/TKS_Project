package org.ias.tks.appports.repoadapters.entities.rent;

import org.ias.tks.appports.repoadapters.entities.Entity;
import org.ias.tks.appports.repoadapters.entities.costume.CostumeEntity;
import org.ias.tks.appports.repoadapters.entities.user.UserEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class RentEntity extends Entity {

    private UserEntity user;
    private List<CostumeEntity> costumes;
    private double price;
    private LocalDate beginTime;
    private LocalDate endTime;


    public RentEntity() {

    }

    public RentEntity(UserEntity user, List<CostumeEntity> costumes, double price, LocalDate beginTime) {
        this.user = user;
        this.costumes = costumes;
        this.price = price;
        this.beginTime = beginTime;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<CostumeEntity> getCostumes() {
        return costumes;
    }

    public void setCostumes(List<CostumeEntity> costumes) {
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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof RentEntity rent)) return false;
//        if (!super.equals(o)) return false;
//        return Double.compare(rent.price, price) == 0 && Objects.equals(user, rent.user) && Objects.equals(costumes, rent.costumes) && Objects.equals(beginTime, rent.beginTime) && Objects.equals(endTime, rent.endTime);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, costumes, price, beginTime, endTime);
    }

    @Override
    public String toString() {
        return "RentEntity{" +
                "user=" + user +
                ", costumes=" + costumes +
                ", price=" + price +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                '}';
    }
}
