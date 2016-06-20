package com.abrsoftware.androidchat.domain.implementation;

import com.abrsoftware.androidchat.domain.useCases.ContactListEvent;
import com.abrsoftware.androidchat.domain.useCases.ContactListInteractor;
import com.abrsoftware.androidchat.domain.useCases.ContactListPresenter;
import com.abrsoftware.androidchat.domain.useCases.ContactListSessionInteractor;
import com.abrsoftware.androidchat.domain.useCases.ContactListView;
import com.abrsoftware.androidchat.entities.User;
import com.abrsoftware.androidchat.lib.Eventbus;
import com.abrsoftware.androidchat.lib.GreenRobotEventbus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by brown on 18/06/16.
 */
public class ContactLictPresenterImpl implements ContactListPresenter {
    private Eventbus eventbus;
    private ContactListView contactListView;
    private ContactListInteractor contactListInteractor;
    private ContactListSessionInteractor sessionInteractor;

    public ContactLictPresenterImpl(ContactListView contactListView) {
        this.contactListView = contactListView;
        eventbus = GreenRobotEventbus.getInstance();
        this.contactListInteractor = new contactListInteractorImpl();
        this.sessionInteractor = new ContactSessionInteractorIml();

    }

    @Override
    public void onCreate() {
        eventbus.register(this);
    }

    @Override
    public void onDestroy() {
        eventbus.unregister(this);
        contactListInteractor.destroyLisener();
        contactListView = null;
    }

    @Override
    public void onResume() {
        sessionInteractor.changeConnectStatus(User.ONLINE);
        contactListInteractor.suscribe();
    }

    @Override
    public void onPause() {
        sessionInteractor.changeConnectStatus(User.OFFLINE);
        contactListInteractor.unsuscribe();
    }

    @Override
    public void signOff() {
        sessionInteractor.changeConnectStatus(User.OFFLINE);
        contactListInteractor.unsuscribe();
        contactListInteractor.destroyLisener();
        sessionInteractor.signOff();
    }

    @Override
    public void removeContact(String mail) {
        contactListInteractor.removeContact(mail);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ContactListEvent event) {
        User user = event.getUser();
        switch (event.getEvenType()){
            case ContactListEvent.onContactAdded:
                onContactAdded(user);
                break;
            case ContactListEvent.onContactChanged:
                onContactChanged(user);
                break;
            case ContactListEvent.onContactRemoved:
                onContactDeleted(user);
                break;
        }


    }

    @Override
    public String getCurrentUserMail() {
        return sessionInteractor.getCurrentUserMail();
    }


    private void onContactAdded(User user){
        if(contactListView != null){
            contactListView.onContactAdded(user);
        }
    }

    private void onContactChanged(User user){
        if(contactListView != null){
            contactListView.onContactChanged(user);
        }
    }

    private void onContactDeleted(User user){
        if(contactListView != null){
            contactListView.onContactDeleted(user);
        }
    }
}
