package com.abrsoftware.androidchat.domain.implementation;

import com.abrsoftware.androidchat.domain.useCases.LoginInteractor;
import com.abrsoftware.androidchat.domain.useCases.LoginRepository;

/**
 * Created by brown on 12/06/16.
 */
public class LoginInteractorImpl implements LoginInteractor {
    private LoginRepository loginRepository;

    public LoginInteractorImpl() {
        loginRepository = new LoginRepositoryImp();
    }

    @Override
    public void checkSesion() {
        loginRepository.checkSesion();
    }

    @Override
    public void doSignIn(String mail, String password) {
        loginRepository.signIn(mail, password);
    }

    @Override
    public void doSignUp(String mail, String password) {
        loginRepository.signUp(mail, password);
    }


}
