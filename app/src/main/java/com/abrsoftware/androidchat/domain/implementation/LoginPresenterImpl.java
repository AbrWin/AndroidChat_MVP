package com.abrsoftware.androidchat.domain.implementation;

import android.util.Log;

import com.abrsoftware.androidchat.domain.useCases.LoginInteractor;
import com.abrsoftware.androidchat.domain.useCases.LoginPresenter;
import com.abrsoftware.androidchat.domain.useCases.LoginView;
import com.abrsoftware.androidchat.event.LoginEvent;
import com.abrsoftware.androidchat.lib.Eventbus;
import com.abrsoftware.androidchat.lib.GreenRobotEventbus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by brown on 12/06/16.
 */
public class LoginPresenterImpl implements LoginPresenter {

    public Eventbus eventbus;
    public LoginView loginView;
    public LoginInteractor loginInteractor;
    public String TAG_NAME = LoginPresenterImpl.class.getName();

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
        this.eventbus = GreenRobotEventbus.getInstance();
    }

    @Override
    public void onCreate() {
        eventbus.register(this);
    }

    @Override
    public void onDestroy() {
        loginView = null;
        eventbus.unregister(this);
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

    @Subscribe
    @Override
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()){
            case LoginEvent.onSignInSuccess:
                onSingInSeccess();
                break;
            case LoginEvent.onSignInError:
                onSignInError(event.getErrorMessage());
                break;
            case LoginEvent.onSignInUpSuccess:
                onSingUpSeccess();
                break;
            case LoginEvent.onSignUpError:
                onSignUpError(event.getErrorMessage());
                break;
            case LoginEvent.onFailedToRecoverSession:
                onFailedRecoverSession();
                break;
        }
    }

    private void onFailedRecoverSession() {
        if(loginView != null){
            loginView.hiddenProgresBar();
            loginView.enableInputs();
        }
        Log.d(TAG_NAME, "onFailedRecoverSession");
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
