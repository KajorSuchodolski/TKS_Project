package org.ias.tks.soapadapters.dto.rent;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ias.tks.soapadapters.dto.ModelDTO;
import org.ias.tks.soapadapters.dto.costume.CostumeOutputSOAP;
import org.ias.tks.soapadapters.dto.user.UserOutputSOAP;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RentOutputSOAP", propOrder = {
        "id",
        "user",
        "costumes",
        "price",
        "beginTime",
        "endTime"
})
public class RentOutputSOAP extends ModelDTO implements Serializable {

    private UserOutputSOAP user;
    private List<CostumeOutputSOAP> costumes;
    private double price;
    private LocalDate beginTime;
    private LocalDate endTime = null;

    public RentOutputSOAP(UserOutputSOAP user, List<CostumeOutputSOAP> costumes, double price, LocalDate beginTime) {
        this.user = user;
        this.costumes = costumes;
        this.price = price;
        this.beginTime = beginTime;
    }

    @Override
    public String getId() {
        return super.getId();
    }


}
