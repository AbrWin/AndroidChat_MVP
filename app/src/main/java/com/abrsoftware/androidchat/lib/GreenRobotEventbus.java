package com.abrsoftware.androidchat.lib;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by brown on 12/06/16.
 */
public class GreenRobotEventbus implements Eventbus {
    public EventBus eventBus;

    private static class SingletonHolder{
        private static final GreenRobotEventbus INSTANCE = new GreenRobotEventbus();
    }

    public GreenRobotEventbus() {
        this.eventBus = EventBus.getDefault();
    }

    public static GreenRobotEventbus getInstance(){
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void register(Object subscriber) {
        eventBus.register(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        eventBus.unregister(subscriber);
    }

    @Override
    public void post(Object event) {
        eventBus.post(event);
    }
}
