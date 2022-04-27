package org.ias.tks.appports.repoadapters.repositories;

import org.ias.tks.appports.repoadapters.entities.Entity;

import java.util.*;

public abstract class AbstractRepository<T extends Entity> {

    private final List<T> repository = new ArrayList<>();


    // C
    public synchronized void add(T t) {
        repository.add(t);
    }

    // R
    public T getById(UUID id) {
        return repository
                .stream()
                .filter(e -> id.equals(e.getId()))
                .findAny()
                .orElse(null);
    }

    public List<T> getAll() {
        return new ArrayList<>(repository);
    }

    // D
    public synchronized void delete(T t) {
        repository.remove(t);
    }


}
