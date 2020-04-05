package com.example.chatEats.push;

import android.text.TextUtils;

import com.example.chatEats.common.app.Application;
import com.example.chatEats.factory.Factory;
import com.igexin.sdk.PushManager;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //调用factory初始化
        Factory.setup();
        //推送初始化
        PushManager.getInstance().initialize(this);

    }
}

