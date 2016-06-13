package com.abrsoftware.androidchat.lib;

/**
 * Created by brown on 12/06/16.
 */
public interface Eventbus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
