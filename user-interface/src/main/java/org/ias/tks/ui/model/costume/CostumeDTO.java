package org.ias.tks.ui.model.costume;

import org.ias.tks.ui.model.EntityDTO;

import java.util.Objects;
import java.util.UUID;

public class CostumeDTO extends EntityDTO {

    private boolean isRented;
    private CostumeSize costumeSize;
    private ForWhom forWhom;
    private String name;
    private double price;
    private boolean selected;
    private UUID id;
    private String etag;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CostumeDTO() {
    }

    public CostumeDTO( String name, CostumeSize costumeSize, ForWhom forWhom, double price) {
        this.costumeSize = costumeSize;
        this.name = name;
        this.price = price;
        this.forWhom = forWhom;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented( boolean rented ) {
        this.isRented = rented;
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

    public String getEtag() {
        return etag;
    }

    public void setEtag( String etag ) {
        this.etag = etag;
    }

//    @Override
//    public boolean equals( Object o ) {
//        if( this == o ) return true;
//        if( !(o instanceof CostumeDTO costumeDTO) ) return false;
//        if( !super.equals(o) ) return false;
//        return isRented == costumeDTO.isRented && costumeSize == costumeDTO.costumeSize && forWhom == costumeDTO.forWhom && Objects.equals(name, costumeDTO.name);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isRented, costumeSize, forWhom, name);
    }

    @Override
    public String toString() {
        return "CostumeDTO{" +
                "isRented=" + isRented +
                ", costumeSize=" + costumeSize +
                ", forWhom=" + forWhom +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", selected=" + selected +
                ", id=" + id +
                ", etag='" + etag + '\'' +
                '}';
    }
}
