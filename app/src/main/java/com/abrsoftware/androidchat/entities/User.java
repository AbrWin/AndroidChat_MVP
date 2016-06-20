package com.abrsoftware.androidchat.entities;

import java.util.Map;

/**
 * Created by brown on 13/06/16.
 */
public class User {
    String email;
    boolean online;
    String urlContactImg;
    Map<String, String> contacts;
    public final static boolean ONLINE = true;
    public final static boolean OFFLINE = false;

    public User(){
    }

    public User(String mail, boolean online, String urlContactImg) {
        this.email = mail;
        this.online = online;
        this.urlContactImg = urlContactImg;
    }

    public String getMail() {
        return email;
    }

    public void setMail(String mail) {
        this.email = mail;
    }

    public boolean isOnline() {
        return online;
    }


    public Map<String, String> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, String> contacts) {
        this.contacts = contacts;
    }

    public String getUrlContactImg() {
        return urlContactImg;
    }

    public void setUrlContactImg(String urlContactImg) {
        this.urlContactImg = urlContactImg;
    }

}
