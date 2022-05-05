package org.ias.tks.ui.model;

import java.util.Objects;
import java.util.UUID;

public abstract class EntityDTO {

    private UUID id;

    public EntityDTO() {
    }

    public UUID getId() {
        return id;
    }


//    @Override
//    public boolean equals( Object o ) {
//        if( this == o ) return true;
//        if( !(o instanceof EntityDTO entityDTO) ) return false;
//        return Objects.equals(id, entityDTO.id);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "EntityDTO{" +
                "id=" + id +
                '}';
    }
}
