package com.abrsoftware.androidchat.domain.implementation;

import android.util.Log;

import com.abrsoftware.androidchat.domain.helper.FireBaseHelper;
import com.abrsoftware.androidchat.domain.useCases.LoginRepository;
import com.abrsoftware.androidchat.event.LoginEvent;
import com.abrsoftware.androidchat.lib.Eventbus;
import com.abrsoftware.androidchat.lib.GreenRobotEventbus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by brown on 12/06/16.
 */
public class LoginRepositoryImp implements LoginRepository {
    private String TAG_NAME = LoginRepository.class.getName();
    private FireBaseHelper helper;

    public LoginRepositoryImp() {
        this.helper = FireBaseHelper.getInstance();
    }

    @Override
    public void signUp(String mail, String password) {
        Log.e(TAG_NAME, "signUp");
        postEvent(LoginEvent.onSignInUpSuccess);
    }

    @Override
    public void signIn(String mail, String password) {
        Log.e(TAG_NAME, "signIn");
        postEvent(LoginEvent.onSignInSuccess);
    }

    @Override
    public void checkSesion() {
        Log.e(TAG_NAME, "checkSesion");
        postEvent(LoginEvent.onFailedToRecoverSession);
    }

    private void postEvent(int type, String error) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if(error != null){
            loginEvent.setErrorMessage(error);
        }

        Eventbus eventBus = GreenRobotEventbus.getInstance();
        eventBus.post(loginEvent);
    }

    private void postEvent(int type){
        postEvent(type, null);
    }
}
