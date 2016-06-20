package com.abrsoftware.androidchat.domain.implementation;

import com.abrsoftware.androidchat.domain.useCases.ContactListInteractor;

/**
 * Created by brown on 18/06/16.
 */
public class contactListInteractorImpl implements ContactListInteractor {

    private final ContactListRepositoryImp repository;

    public contactListInteractorImpl() {
        repository = new ContactListRepositoryImp();
    }

    @Override
    public void suscribe() {
        repository.suscribeContactListEvents();
    }

    @Override
    public void unsuscribe() {
        repository.unsuscribeContactListEvents();
    }

    @Override
    public void destroyLisener() {
        repository.destroyListener();
    }

    @Override
    public void removeContact(String mail) {
        repository.removeContact(mail);
    }
}
