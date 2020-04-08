package com.example.chatEats.push;

import com.example.chatEats.common.app.Application;
import com.example.chatEats.factory.Factory;
import com.igexin.sdk.PushManager;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Factory.setup();
        PushManager.getInstance().initialize(this);
    }
}
