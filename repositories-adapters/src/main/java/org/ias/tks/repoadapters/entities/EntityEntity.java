package org.ias.tks.repoadapters.entities;

import java.util.Objects;
import java.util.UUID;

public abstract class EntityEntity {

    private UUID id;

    public EntityEntity() {

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
        if (!(o instanceof EntityEntity entityEntity)) return false;
        return Objects.equals(id, entityEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "EntityEntity{" +
                "id=" + id +
                '}';
    }
}
