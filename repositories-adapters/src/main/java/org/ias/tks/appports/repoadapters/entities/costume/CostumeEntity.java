package org.ias.tks.appports.repoadapters.entities.costume;

import org.ias.tks.appports.repoadapters.entities.Entity;
import org.ias.tks.appports.repoadapters.entities.SingableEntity;

import java.util.Objects;
import java.util.UUID;

public class CostumeEntity extends Entity implements SingableEntity {

    private boolean isRented;
    private CostumeSizeEnt costumeSizeEnt;
    private ForWhomEnt forWhomEnt;
    private String name;
    private double price;

    public CostumeEntity() {
    }

    public CostumeEntity(String name, CostumeSizeEnt costumeSizeEnt, ForWhomEnt forWhomEnt, double price) {
        this.isRented = false;
        this.costumeSizeEnt = costumeSizeEnt;
        this.name = name;
        this.price = price;
        this.forWhomEnt = forWhomEnt;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public CostumeSizeEnt getCostumeSize() {
        return costumeSizeEnt;
    }

    public void setCostumeSize(CostumeSizeEnt costumeSizeEnt) {
        this.costumeSizeEnt = costumeSizeEnt;
    }

    public ForWhomEnt getForWhom() {
        return forWhomEnt;
    }

    public void setForWhom(ForWhomEnt forWhomEnt) {
        this.forWhomEnt = forWhomEnt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public UUID getId() {
        return super.getId();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CostumeEntity costumeEnt)) return false;
        if (!super.equals(o)) return false;
        return isRented == costumeEnt.isRented && costumeSizeEnt == costumeEnt.costumeSizeEnt && forWhomEnt == costumeEnt.forWhomEnt && Objects.equals(name, costumeEnt.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isRented, costumeSizeEnt, forWhomEnt, name);
    }

    @Override
    public String toString() {
        return "CostumeEntity{" + "isRented=" + isRented + ", costumeSizeEnt=" + costumeSizeEnt + ", forWhomEnt=" + forWhomEnt + ", name='" + name + '\'' + '}';
    }

    @Override
    public String getSingablePayload() {
        return getId().toString();
    }
}
