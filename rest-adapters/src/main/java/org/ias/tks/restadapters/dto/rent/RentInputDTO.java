package org.ias.tks.restadapters.dto.rent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ias.tks.restadapters.dto.ModelDTO;
import org.ias.tks.restadapters.dto.costume.CostumeDTO;
import org.ias.tks.restadapters.dto.user.UserInputDto;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class RentInputDTO extends ModelDTO {

    @NotNull
    private String login;
    @NotNull
    private UUID costumeUUID;
    @NotNull
    private LocalDate date;


    public RentInputDTO(String login, UUID costumesUUID, LocalDate date) {
        this.login = login;
        this.costumeUUID = costumesUUID;
        this.date = date;
    }

    @Override
    public UUID getId() {
        return super.getId();
    }


}
