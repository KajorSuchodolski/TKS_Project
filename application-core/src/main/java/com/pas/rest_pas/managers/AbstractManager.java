package com.pas.rest_pas.managers;

import com.pas.rest_pas.repositories.AbstractRepository;


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
