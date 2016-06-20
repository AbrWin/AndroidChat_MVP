package com.abrsoftware.androidchat.domain.implementation;

import com.abrsoftware.androidchat.domain.helper.FireBaseHelper;
import com.abrsoftware.androidchat.domain.useCases.ContactListEvent;
import com.abrsoftware.androidchat.domain.useCases.ContactListRepository;
import com.abrsoftware.androidchat.entities.User;
import com.abrsoftware.androidchat.lib.Eventbus;
import com.abrsoftware.androidchat.lib.GreenRobotEventbus;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

import java.util.HashMap;

/**
 * Created by brown on 18/06/16.
 */
public class ContactListRepositoryImp implements ContactListRepository {
    private FireBaseHelper  helper;
    private ChildEventListener contactEventListener;
    private Eventbus eventbus;

    public ContactListRepositoryImp() {
        this.eventbus = GreenRobotEventbus.getInstance();
        this.helper = FireBaseHelper.getInstance();
    }

    @Override
    public void signOff() {
        helper.signOff();
    }

    @Override
    public String getCurrentUserMail() {
        return helper.getUserAuthEmail();
    }

    @Override
    public void removeContact(String mail) {
        String myEmail = helper.getUserAuthEmail();
        helper.getOneContactReference(myEmail, mail).removeValue();
        helper.getOneContactReference(mail, myEmail).removeValue();

    }

    @Override
    public void suscribeContactListEvents() {
        if(contactEventListener == null){
            contactEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {                    
                    handleContact(dataSnapshot, ContactListEvent.onContactAdded);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    handleContact(dataSnapshot, ContactListEvent.onContactChanged);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    handleContact(dataSnapshot, ContactListEvent.onContactRemoved);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

                @Override
                public void onCancelled(FirebaseError firebaseError) {}
            };
        }
        helper.getMyUserReference().addChildEventListener(contactEventListener);
    }

    private void handleContact(DataSnapshot dataSnapshot, int type) {
        String mail =  dataSnapshot.getKey();
        mail.replace("_",".");
        boolean online = ((Boolean) dataSnapshot.getValue()).booleanValue();
        User user = new User(mail, online, "");
        post(type, user);
    }

    private void post(int type, User user) {
        ContactListEvent event = new ContactListEvent();
        event.setEvenType(type);
        event.setUser(user);
        eventbus.post(event);

    }

    @Override
    public void unsuscribeContactListEvents() {
        if(contactEventListener != null){
            helper.getMyUserReference().removeEventListener(contactEventListener);
        }
    }

    @Override
    public void destroyListener() {
        contactEventListener = null;
    }

    @Override
    public void changeConnectStatus(boolean status) {

    }
}
