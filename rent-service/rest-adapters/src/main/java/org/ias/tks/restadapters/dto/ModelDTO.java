package org.ias.tks.restadapters.dto;

import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Data
public abstract class ModelDTO {

    private UUID id;

    public ModelDTO() {

        this.id = UUID.randomUUID();
    }

}
