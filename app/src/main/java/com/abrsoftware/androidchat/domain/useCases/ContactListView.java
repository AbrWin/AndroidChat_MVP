package com.abrsoftware.androidchat.domain.useCases;

import com.abrsoftware.androidchat.entities.User;

/**
 * Created by brown on 15/06/16.
 */
public interface ContactListView {
    void onContactAdded(User user);
    void onContactChanged(User user);
    void onContactDeleted(User user);

}
