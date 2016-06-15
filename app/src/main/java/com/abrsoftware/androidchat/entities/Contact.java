package com.abrsoftware.androidchat.entities;

/**
 * Created by json on 15/06/16.
 */
public class Contact {
    public String mailContact;
    public String statusContact;
    public String urlContactImg;

    public Contact(String mailContact, String statusContact, String urlContactImg) {
        this.mailContact = mailContact;
        this.statusContact = statusContact;
        this.urlContactImg = urlContactImg;
    }

    public String getMailContact() {
        return mailContact;
    }

    public void setMailContact(String mailContact) {
        this.mailContact = mailContact;
    }

    public String getStatusContact() {
        return statusContact;
    }

    public void setStatusContact(String statusContact) {
        this.statusContact = statusContact;
    }

    public String getUrlContactImg() {
        return urlContactImg;
    }

    public void setUrlContactImg(String urlContactImg) {
        this.urlContactImg = urlContactImg;
    }
}
