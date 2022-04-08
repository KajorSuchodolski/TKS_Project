package org.ias.tks.restadapters.dto.costume;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ias.tks.appcore.domainmodel.model.costume.CostumeSize;
import org.ias.tks.appcore.domainmodel.model.costume.ForWhom;
import org.ias.tks.restadapters.dto.ModelDTO;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CostumeDTO extends ModelDTO {

    private boolean isRented;
    private CostumeSize costumeSize;
    private ForWhom forWhom;
    private String name;
    private double price;


    public CostumeDTO(String name, CostumeSize costumeSize, ForWhom forWhom, double price) {
        this.isRented = false;
        this.costumeSize = costumeSize;
        this.name = name;
        this.price = price;
        this.forWhom = forWhom;
    }

    @Override
    public UUID getId() {
        return super.getId();
    }
}
