package org.ias.tks.appcore.managers;

import org.ias.tks.appcore.repositories.AbstractRepository;


public abstract class AbstractManager {

    private AbstractRepository repository;

    public AbstractManager() {

    }

    public AbstractManager(AbstractRepository repository) {
        this.repository = repository;
    }

    public AbstractRepository getRepository() {
        return repository;
    }

    public void setRepository( AbstractRepository repository ) {
        this.repository = repository;
    }
}
