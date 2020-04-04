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

                //请求成功返回
                //返回中得到全局model得到全局model,内部是使用的Gson进行解析
                RspModel<AccountRspModel> rspModel = response.body();
                //
                if (rspModel.success()) {
                    //拿到实体
                    AccountRspModel accountRspModel = rspModel.getResult();

                    if (accountRspModel.isBind()) {
                        // 数据库写入？
                        User user = accountRspModel.getUser();
                        callback.onDataLoaded(user);
                    }
                    else {

                        bindPush(callback);
                    }

                }
                else {
                    //callback.onDataNotAvailable();
                    //错误解析 格式化
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<AccountRspModel>> call, Throwable t) {

                //网络请求失败
                callback.onDataNotAvailable(R.string.app_name);


            }
        });
    }


    public static void bindPush(final DataSource.Callback<User> callback) {
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
