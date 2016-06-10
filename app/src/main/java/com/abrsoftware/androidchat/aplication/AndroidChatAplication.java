package com.abrsoftware.androidchat.aplication;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by json on 9/06/16.
 */
public class AndroidChatAplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        setUpFireBase();
    }

    private void setUpFireBase() {
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
