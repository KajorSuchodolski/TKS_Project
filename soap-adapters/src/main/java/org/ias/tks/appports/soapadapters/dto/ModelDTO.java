package org.ias.tks.appports.soapadapters.dto;

import lombok.Data;

import java.util.UUID;

@Data
public abstract class ModelDTO {

    private String id;

    public ModelDTO() {

        this.id = UUID.randomUUID().toString();
    }

}
