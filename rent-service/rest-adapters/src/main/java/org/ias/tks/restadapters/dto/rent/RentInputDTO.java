package org.ias.tks.restadapters.dto.rent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ias.tks.restadapters.dto.ModelDTO;
import org.ias.tks.restadapters.dto.costume.CostumeDTO;
import org.ias.tks.restadapters.dto.user.UserInputDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class RentInputDTO extends ModelDTO {

    private UserInputDto user;
    private List<CostumeDTO> costumes;
    private double price;
    private LocalDate beginTime;
    private LocalDate endTime;

    public RentInputDTO(UserInputDto user, List<CostumeDTO> costumes, double price, LocalDate beginTime) {
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
