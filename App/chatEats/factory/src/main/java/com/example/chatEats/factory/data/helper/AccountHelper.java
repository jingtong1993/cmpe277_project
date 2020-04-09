package com.example.chatEats.factory.data.helper;

import com.example.chatEats.factory.Factory;
import com.example.chatEats.factory.R;
import com.example.chatEats.factory.data.DataSource;
import com.example.chatEats.factory.model.api.RspModel;
import com.example.chatEats.factory.model.api.account.AccountRspModel;
import com.example.chatEats.factory.model.api.account.RegisterModel;
import com.example.chatEats.factory.model.db.User;
import com.example.chatEats.factory.net.Network;
import com.example.chatEats.factory.net.RemoteService;
import com.example.chatEats.factory.persistence.Account;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountHelper {

    public static void register(RegisterModel model, final DataSource.Callback<User> callback) {
        RemoteService service = Network.getRetrofit().create(RemoteService.class);

        // 得到一个Call
        Call<RspModel<AccountRspModel>> call = service.accountRegister(model);
        // 异步的请求
        call.enqueue(new Callback<RspModel<AccountRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<AccountRspModel>> call, Response<RspModel<AccountRspModel>> response) {
                RspModel<AccountRspModel> rspModel = response.body();
                if (rspModel.success()) {
                    AccountRspModel accountRspModel = rspModel.getResult();

                    if (accountRspModel.isBind()) {
                        User user = accountRspModel.getUser();
                        callback.onDataLoaded(user);
                    }
                    else {
                        //callback.onDataLoaded(accountRspModel.getUser());
                        bindPush(callback);
                    }

                }
                else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<AccountRspModel>> call, Throwable t) {
//                if (t instanceof IOException) {
//                    t.printStackTrace();
//                    callback.onDataNotAvailable(R.string.data_network_error);
//                }
//                else {
//                    callback.onDataNotAvailable(R.string.data_rsp_error_unknown);
//                }
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }


    public static void bindPush(final DataSource.Callback<User> callback) {
        Account.setBind(true);

        // 检查是否为空
//        String pushId = Account.getPushId();
//        if (TextUtils.isEmpty(pushId))
//            return;
//
//        // 调用Retrofit对我们的网络请求接口做代理
//        RemoteService service = Network.remote();
//        Call<RspModel<AccountRspModel>> call = service.accountBind(pushId);
//        call.enqueue(new AccountRspCallback(callback));
    }
}
