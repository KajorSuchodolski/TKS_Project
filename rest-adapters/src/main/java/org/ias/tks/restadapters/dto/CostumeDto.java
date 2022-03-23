package org.ias.tks.restadapters.dto;

import org.ias.tks.appcore.domainmodel.model.costume.CostumeSize;
import org.ias.tks.appcore.domainmodel.model.costume.ForWhom;

public class CostumeDto {
    private boolean isRented;
    private CostumeSize costumeSize;
    private ForWhom forWhom;
    private String name;
    private double price;

    public CostumeDto() {
    }

    public CostumeDto(String name, CostumeSize costumeSize, ForWhom forWhom, double price) {
        this.isRented = false;
        this.costumeSize = costumeSize;
        this.name = name;
        this.price = price;
        this.forWhom = forWhom;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public CostumeSize getCostumeSize() {
        return costumeSize;
    }

    public void setCostumeSize(CostumeSize costumeSize) {
        this.costumeSize = costumeSize;
    }

    public ForWhom getForWhom() {
        return forWhom;
    }

    public void setForWhom(ForWhom forWhom) {
        this.forWhom = forWhom;
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
}
