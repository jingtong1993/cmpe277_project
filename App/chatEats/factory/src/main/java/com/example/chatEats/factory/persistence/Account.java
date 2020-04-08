package com.example.chatEats.factory.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.chatEats.factory.Factory;

public class Account {
    private static final String KEY_PUSH_ID = "KEY_PUSH_ID";
    private static final String KEY_IS_BIND = "KEY_IS_BIND";
    private static final String KEY_TOKEN = "KEY_TOKEN";
    private static final String KEY_USER_ID = "KEY_USER_ID";
    private static final String KEY_ACCOUNT = "KEY_ACCOUNT";

    private static String pushId = "test";

    private static void save(Context context) {
        // 获取数据持久化的SP
        SharedPreferences sp = context.getSharedPreferences(Account.class.getName(),
                Context.MODE_PRIVATE);
        // 存储数据
        sp.edit()
                .putString(KEY_PUSH_ID, pushId)
//                .putBoolean(KEY_IS_BIND, isBind)
//                .putString(KEY_TOKEN, token)
//                .putString(KEY_USER_ID, userId)
//                .putString(KEY_ACCOUNT, account)
                .apply();
    }

    public static void load(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Account.class.getName(),
                Context.MODE_PRIVATE);
        pushId = sp.getString(KEY_PUSH_ID, "");
//        isBind = sp.getBoolean(KEY_IS_BIND, false);
//        token = sp.getString(KEY_TOKEN, "");
//        userId = sp.getString(KEY_USER_ID, "");
//        account = sp.getString(KEY_ACCOUNT, "");
    }


    public static String getPushId() {
        return pushId;
    }

    public static void setPushId(String pushId) {
        Account.pushId = pushId;
        Account.save(Factory.app());
    }

    public static boolean isLogin() {
        return true;
    }

    public static boolean isBind() {
        return false;
    }
}
