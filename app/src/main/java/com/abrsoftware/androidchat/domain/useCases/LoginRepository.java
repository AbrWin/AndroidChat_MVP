package com.abrsoftware.androidchat.domain.useCases;

/**
 * Created by json on 10/06/16.
 */
public interface LoginRepository {
    void signUp(String mail, String password);
    void signIn(String mail, String password);
    void checkSesion();
}
