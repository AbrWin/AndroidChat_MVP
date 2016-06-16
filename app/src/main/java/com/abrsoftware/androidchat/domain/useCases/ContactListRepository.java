package com.abrsoftware.androidchat.domain.useCases;

/**
 * Created by brown on 15/06/16.
 */
public interface ContactListRepository {
    void signOff();
    String getCurrentUserMail();
    void removeContact(String mail);
    void suscribeContactListEvents();
    void unsuscribeContactListEvents();
    void destroyListener();
    void changeConnectStatus(boolean status);
}
