package org.ias.tks.model.rent;

import org.ias.tks.model.costume.Costume;
import org.ias.tks.model.Model;
import org.ias.tks.model.user.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Rent extends Model {

    private User user;
    private List<Costume> costumes;
    private double price;
    private LocalDate beginTime;
    private LocalDate endTime;


    public Rent() {

    }

    public Rent( User user, List<Costume> costumes, double price, LocalDate beginTime) {
        this.user = user;
        this.costumes = costumes;
        this.price = price;
        this.beginTime = beginTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

    public List<Costume> getCostumes() {
        return costumes;
    }

    public void setCostumes( List<Costume> costumes ) {
        this.costumes = costumes;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice( double price ) {
        this.price = price;
    }

    public LocalDate getBeginTime() {
        return beginTime;
    }

    public void setBeginTime( LocalDate beginTime ) {
        this.beginTime = beginTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime( LocalDate endTime ) {
        this.endTime = endTime;
    }

    @Override
    public UUID getId() {
        return super.getId();
    }

    @Override
    public boolean equals( Object o ) {
        if( this == o ) return true;
        if( !(o instanceof Rent) ) return false;
        if( !super.equals(o) ) return false;
        Rent rent = (Rent) o;
        return Double.compare(rent.price, price) == 0 && Objects.equals(user, rent.user) && Objects.equals(costumes, rent.costumes) && Objects.equals(beginTime, rent.beginTime) && Objects.equals(endTime, rent.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, costumes, price, beginTime, endTime);
    }

    @Override
    public String toString() {
        return "Rent{" +
                "user=" + user +
                ", costumes=" + costumes +
                ", price=" + price +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                '}';
    }
}
