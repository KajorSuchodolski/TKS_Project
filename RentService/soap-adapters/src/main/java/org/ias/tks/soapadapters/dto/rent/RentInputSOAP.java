package org.ias.tks.soapadapters.dto.rent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ias.tks.soapadapters.dto.ModelDTO;


import javax.xml.bind.annotation.XmlSchemaType;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor


public class RentInputSOAP extends ModelDTO {

    private String userUUID;
    private String costumeUUID;
    private double price;
    @XmlSchemaType(name = "date")
    private LocalDate beginTime;
    @XmlSchemaType(name = "date")
    private LocalDate endTime;

    public RentInputSOAP(String userUUID, String costumeUUID, double price, LocalDate beginTime) {
        this.userUUID = userUUID;
        this.costumeUUID = costumeUUID;
        this.price = price;
        this.beginTime = beginTime;
    }

    @Override
    public String getId() {
        return super.getId();
    }


}
