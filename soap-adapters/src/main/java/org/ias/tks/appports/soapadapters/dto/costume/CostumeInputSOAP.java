package org.ias.tks.appports.soapadapters.dto.costume;


import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ias.tks.appports.soapadapters.dto.ModelDTO;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@XmlType(name = "CostumeSOAP", propOrder = {
        "name",
        "costumeSize",
        "forWhom",
        "price"
})
public class CostumeInputSOAP extends ModelDTO {

    private boolean isRented;

    private String costumeSize;

    private String forWhom;

    private String name;

    private double price;

    public CostumeInputSOAP(String name, String costumeSize, String forWhom, double price) {
        this.isRented = false;
        this.costumeSize = costumeSize;
        this.name = name;
        this.price = price;
        this.forWhom = forWhom;
    }

}
