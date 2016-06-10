package com.abrsoftware.androidchat.domain.useCases;

/**
 * Created by json on 10/06/16.
 */
public interface LoginView {
    void enableInputs();
    void disableInputs();
    void showProgresBar();
    void hiddenProgresBar();

    void handSingOut();
    void handSingI();

    void navigateToMainScreen();
    void loginError(String error);

    void newUserSucces();
    void newUserError();

}
