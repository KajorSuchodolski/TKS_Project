package org.ias.tks.appports.repoadapters.entities;

import java.util.Objects;
import java.util.UUID;

public abstract class Entity {

    private UUID id;

    public Entity() {

        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Entity entity)) return false;
//        return Objects.equals(id, entity.id);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }
}
