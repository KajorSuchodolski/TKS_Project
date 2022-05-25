package org.ias.tks.soapadapters.dto.costume;

import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@XmlType(name = "CostumeOutputSOAP", propOrder = {
        "id",
        "name",
        "costumeSize",
        "forWhom",
        "price",
        "isRented"
})
public class CostumeOutputSOAP {

    private String id;

    private boolean isRented;

    private String costumeSize;

    private String forWhom;

    private String name;

    private double price;

    public CostumeOutputSOAP(String name, String costumeSize, String forWhom, double price) {
        this.isRented = false;
        this.costumeSize = costumeSize;
        this.name = name;
        this.price = price;
        this.forWhom = forWhom;
    }

}
