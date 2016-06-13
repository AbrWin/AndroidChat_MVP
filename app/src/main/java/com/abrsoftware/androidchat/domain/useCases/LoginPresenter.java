package com.abrsoftware.androidchat.domain.useCases;

import com.abrsoftware.androidchat.event.LoginEvent;

/**
 * Created by json on 10/06/16.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticateUser();
    void validateLogin(String mail, String password);
    void registerNewUser(String mail, String password);
    void onEventMainThread(LoginEvent event);
}
