

package org.ias.tks.appports.soapadapters.dto.rent;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ias.tks.appports.soapadapters.dto.ModelDTO;
import org.ias.tks.appports.soapadapters.dto.costume.CostumeOutputSOAP;
import org.ias.tks.appports.soapadapters.dto.user.UserOutputSOAP;


import javax.xml.bind.annotation.XmlSchemaType;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RentOutputSOAP", propOrder = {
        "id",
        "user",
        "costumes",
        "price",
        "beginTime",
        "endTime"
})

public class RentOutputSOAP implements Serializable {

    private String id;
    private UserOutputSOAP user;
    private List<CostumeOutputSOAP> costumes;
    private double price;

    //@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @XmlSchemaType(name = "date")
    private LocalDate beginTime;

    //@XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @XmlSchemaType(name = "date")
    private LocalDate endTime;

    public RentOutputSOAP(UserOutputSOAP user, List<CostumeOutputSOAP> costumes, double price, LocalDate beginTime, LocalDate endTime) {
        this.user = user;
        this.costumes = costumes;
        this.price = price;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }
}
