package org.ias.tks.appcore.domainmodel.model.costume;

import org.ias.tks.appcore.domainmodel.model.Model;

import java.util.Objects;
import java.util.UUID;

public class Costume extends Model {

    private boolean isRented;
    private CostumeSize costumeSize;
    private ForWhom forWhom;
    private String name;
    private double price;

    public Costume() {
    }

    public Costume(String name, CostumeSize costumeSize, ForWhom forWhom, double price) {
        this.isRented = false;
        this.costumeSize = costumeSize;
        this.name = name;
        this.price = price;
        this.forWhom = forWhom;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented( boolean rented ) {
        isRented = rented;
    }

    public CostumeSize getCostumeSize() {
        return costumeSize;
    }

    public void setCostumeSize( CostumeSize costumeSize ) {
        this.costumeSize = costumeSize;
    }

    public ForWhom getForWhom() {
        return forWhom;
    }

    public void setForWhom( ForWhom forWhom ) {
        this.forWhom = forWhom;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
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
    public boolean equals( Object o ) {
        if( this == o ) return true;
        if( !(o instanceof Costume) ) return false;
        if( !super.equals(o) ) return false;
        Costume costume = (Costume) o;
        return isRented == costume.isRented && costumeSize == costume.costumeSize && forWhom == costume.forWhom && Objects.equals(name, costume.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isRented, costumeSize, forWhom, name);
    }

    @Override
    public String toString() {
        return "Costume{" +
                "isRented=" + isRented +
                ", costumeSize=" + costumeSize +
                ", forWhom=" + forWhom +
                ", name='" + name + '\'' +
                '}';
    }

}
