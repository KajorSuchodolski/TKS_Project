package org.ias.tks.appcore.domainmodel.model;

import java.util.Objects;
import java.util.UUID;

public abstract class Model {

    private UUID id;

    public Model() {

        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId( UUID id ) {
        this.id = id;
    }

    @Override
    public boolean equals( Object o ) {
        if( this == o ) return true;
        if( !(o instanceof Model model) ) return false;
        return Objects.equals(id, model.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                '}';
    }
}
