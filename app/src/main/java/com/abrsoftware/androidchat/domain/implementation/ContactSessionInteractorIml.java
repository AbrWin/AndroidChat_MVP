package com.abrsoftware.androidchat.domain.implementation;

import com.abrsoftware.androidchat.domain.useCases.ContactListRepository;
import com.abrsoftware.androidchat.domain.useCases.ContactListSessionInteractor;

/**
 * Created by brown on 18/06/16.
 */
public class ContactSessionInteractorIml implements ContactListSessionInteractor {
    public ContactListRepository repository;


    public ContactSessionInteractorIml() {
        repository = new ContactListRepositoryImp();
    }

    @Override
    public void signOff() {
        repository.signOff();
    }

    @Override
    public String getCurrentUserMail() {
        return repository.getCurrentUserMail();
    }

    @Override
    public void changeConnectStatus(boolean status) {
        repository.changeConnectStatus(status);
    }
}
