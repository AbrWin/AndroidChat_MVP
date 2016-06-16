package com.abrsoftware.androidchat.domain.useCases;

/**
 * Created by brown on 15/06/16.
 */
public interface ContactListInteractor {
    void suscribe();
    void unsuscribe();
    void destroyLisener();
    void removeContactEmail();
}
