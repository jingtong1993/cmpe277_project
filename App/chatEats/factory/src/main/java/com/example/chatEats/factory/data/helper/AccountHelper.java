package com.example.chatEats.factory.data.helper;

import com.example.chatEats.factory.R;
import com.example.chatEats.factory.data.DataSource;
import com.example.chatEats.factory.model.api.account.RegisterModel;
import com.example.chatEats.factory.model.db.User;

public class AccountHelper {

    public static void register(RegisterModel model, final DataSource.Callback<User> callback) {
        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                callback.onDataNotAvailable(R.string.data_rsp_error_parameters);
            }
        }.start();
    }
}
