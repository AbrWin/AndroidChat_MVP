package com.abrsoftware.androidchat.domain.implementation;

import com.abrsoftware.androidchat.domain.useCases.LoginInteractor;
import com.abrsoftware.androidchat.domain.useCases.LoginPresenter;
import com.abrsoftware.androidchat.domain.useCases.LoginView;

/**
 * Created by brown on 12/06/16.
 */
public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void checkForAuthenticateUser() {
        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgresBar();
        }
        loginInteractor.checkSesion();
    }

    @Override
    public void validateLogin(String mail, String password) {
        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgresBar();
        }
        loginInteractor.doSignIn(mail, password);
    }

    @Override
    public void registerNewUser(String mail, String password) {
        if(loginView != null){
            loginView.disableInputs();
            loginView.showProgresBar();
        }
        loginInteractor.doSignUp(mail, password);
    }

    private void onSingInSeccess(){
        if(loginView != null){
            loginView.navigateToMainScreen();
        }
    }

    private void onSingUpSeccess(){
        if(loginView != null){
            loginView.newUserSucces();
        }
    }

    private void onSignInError(String error){
        if(loginView != null){
            loginView.hiddenProgresBar();
            loginView.enableInputs();
            loginView.loginError(error);
        }
    }

    private void onSignUpError(String error){
        if(loginView != null){
            loginView.hiddenProgresBar();
            loginView.enableInputs();
            loginView.newUserError(error);
        }
    }

}
