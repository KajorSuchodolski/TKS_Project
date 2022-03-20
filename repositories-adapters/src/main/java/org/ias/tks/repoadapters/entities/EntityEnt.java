package org.ias.tks.repoadapters.entities;

import java.util.Objects;
import java.util.UUID;

public abstract class EntityEnt {

    private UUID id;

    public EntityEnt() {

        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityEnt entityEnt)) return false;
        return Objects.equals(id, entityEnt.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "EntityEnt{" +
                "id=" + id +
                '}';
    }
}
