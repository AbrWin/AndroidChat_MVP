package com.abrsoftware.androidchat.domain.implementation;

import android.util.Log;

import com.abrsoftware.androidchat.domain.helper.FireBaseHelper;
import com.abrsoftware.androidchat.domain.useCases.LoginRepository;
import com.abrsoftware.androidchat.entities.User;
import com.abrsoftware.androidchat.event.LoginEvent;
import com.abrsoftware.androidchat.lib.Eventbus;
import com.abrsoftware.androidchat.lib.GreenRobotEventbus;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;


/**
 * Created by brown on 12/06/16.
 */
public class LoginRepositoryImp implements LoginRepository {
    private String TAG_NAME = LoginRepository.class.getName();
    private FireBaseHelper helper;
    private Firebase dataReference;
    private Firebase myUserReference;

    public LoginRepositoryImp() {
        this.helper = FireBaseHelper.getInstance();
        this.dataReference = helper.getDataReference();
        this.myUserReference = helper.getMyUserReference();
    }

    @Override
    public void signUp(final String mail, final String password) {
        Log.e(TAG_NAME, "signUp");
        dataReference.createUser(mail, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                postEvent(LoginEvent.onSignUpSuccess);


                signIn(mail, password);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                postEvent(LoginEvent.onSignUpError, firebaseError.getMessage());
            }
        });

    }

    @Override
    public void signIn(String mail, String password) {
        Log.e(TAG_NAME, "signIn");
        dataReference.authWithPassword(mail, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                initSignIn();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                postEvent(LoginEvent.onSignUpError, firebaseError.getMessage());
            }
        });
        //
    }

    @Override
    public void checkSesion() {
        Log.e(TAG_NAME, "checkSesion");
        if(dataReference.getAuth() != null){
            initSignIn();
        }else{
            postEvent(LoginEvent.onFailedToRecoverSession);
        }
    }

    private void initSignIn(){
        myUserReference = helper.getMyUserReference();
        myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User currenUser = dataSnapshot.getValue(User.class);

                if(currenUser == null){
                    String mail = helper.getUserAuthEmail();
                    if(mail != null){
                        currenUser = new User();
                        currenUser.setMail(mail);
                        myUserReference.setValue(currenUser);


                    }
                }
                helper.changeUserStatusConnection(User.ONLINE);
                postEvent(LoginEvent.onSignInSuccess);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
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
