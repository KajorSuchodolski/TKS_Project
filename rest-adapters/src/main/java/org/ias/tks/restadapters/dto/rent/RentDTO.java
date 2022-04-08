package org.ias.tks.restadapters.dto.rent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ias.tks.appcore.domainmodel.model.costume.Costume;
import org.ias.tks.appcore.domainmodel.model.user.User;
import org.ias.tks.restadapters.dto.ModelDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class RentDTO extends ModelDTO {

    private User user;
    private List<Costume> costumes;
    private double price;
    private LocalDate beginTime;
    private LocalDate endTime;

    public RentDTO(User user, List<Costume> costumes, double price, LocalDate beginTime) {
        this.user = user;
        this.costumes = costumes;
        this.price = price;
        this.beginTime = beginTime;
    }

    @Override
    public UUID getId() {
        return super.getId();
    }


}
