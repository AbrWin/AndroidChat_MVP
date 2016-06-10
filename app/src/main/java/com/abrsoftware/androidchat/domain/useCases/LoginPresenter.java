package com.abrsoftware.androidchat.domain.useCases;

/**
 * Created by json on 10/06/16.
 */
public interface LoginPresenter {
    void onDestroy();
    void checkForAuthenticateUser();
    void validateLogin(String mail, String password);
    void registerNewUser(String mail, String password);
}
