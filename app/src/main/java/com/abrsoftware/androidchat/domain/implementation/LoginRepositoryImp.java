package com.abrsoftware.androidchat.domain.implementation;

import android.util.Log;

import com.abrsoftware.androidchat.domain.helper.FireBaseHelper;
import com.abrsoftware.androidchat.domain.useCases.LoginRepository;

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

    }

    @Override
    public void signIn(String mail, String password) {
        Log.e(TAG_NAME, "signIn");

    }

    @Override
    public void checkSesion() {
        Log.e(TAG_NAME, "checkSesion");
    }
}
