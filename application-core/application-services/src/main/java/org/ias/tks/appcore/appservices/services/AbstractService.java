package org.ias.tks.appcore.appservices.services;

import org.ias.tks.appports.repoadapters.repositories.AbstractRepository;


public abstract class AbstractService {

    private AbstractRepository repository;

    public AbstractService() {

    }

    public AbstractService(AbstractRepository repository) {
        this.repository = repository;
    }

    public AbstractRepository getRepository() {
        return repository;
    }

    public void setRepository( AbstractRepository repository ) {
        this.repository = repository;
    }
}
