package com.abrsoftware.androidchat.entities;

import java.util.Map;

/**
 * Created by brown on 13/06/16.
 */
public class User {
    String mail;
    boolean online;
    Map<String, Boolean> contacts;
    public final static boolean ONLINE = true;
    public final static boolean OFFLINE = false;

    public User(){
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public Map<String, Boolean> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, Boolean> contacts) {
        this.contacts = contacts;
    }
}
