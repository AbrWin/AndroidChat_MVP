package com.abrsoftware.androidchat.domain.useCases;

import com.abrsoftware.androidchat.entities.User;

/**
 * Created by brown on 15/06/16.
 */
public class ContactListEvent {
    public final static int onContactAdded = 0;
    public final static int onContactChanged = 1;
    public final static int onContactRemoved = 2;

    private User user;
    private int evenType;

    public int getEvenType() {
        return evenType;
    }

    public void setEvenType(int evenType) {
        this.evenType = evenType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
