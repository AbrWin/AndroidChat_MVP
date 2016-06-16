package com.abrsoftware.androidchat.domain.useCases;

/**
 * Created by brown on 15/06/16.
 */
public interface ContactListPresenter {
    void onCreate();
    void onDestroy();
    void onResuome();
    void onPause();

    void signOff();
    void removeContact(String mail);
    void onEventMainThread(ContactListEvent event);

    String getCurrentUserMail();
}
