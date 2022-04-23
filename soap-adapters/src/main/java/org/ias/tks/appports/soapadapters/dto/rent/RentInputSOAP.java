package org.ias.tks.appports.soapadapters.dto.rent;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ias.tks.appports.soapadapters.dto.ModelDTO;



import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RentOutputSOAP", propOrder = {
        "login",
        "costumeUUID",
        "beginTime",
})
public class RentInputSOAP extends ModelDTO {

    private String login;
    private String costumeUUID;
    private LocalDate beginTime;

    public RentInputSOAP(String login, String costumeUUID, LocalDate beginTime) {
        this.login = login;
        this.costumeUUID = costumeUUID;
        this.beginTime = beginTime;
    }


}
