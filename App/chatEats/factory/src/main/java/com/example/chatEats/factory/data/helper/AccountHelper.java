package com.example.chatEats.factory.data.helper;

import com.example.chatEats.factory.Factory;
import com.example.chatEats.factory.R;
import com.example.chatEats.factory.data.DataSource;
import com.example.chatEats.factory.model.api.RspModel;
import com.example.chatEats.factory.model.api.account.AccountRspModel;
import com.example.chatEats.factory.model.api.account.RegisterModel;
import com.example.chatEats.factory.model.db.AppDatabase;
import com.example.chatEats.factory.model.db.User;
import com.example.chatEats.factory.net.Network;
import com.example.chatEats.factory.net.RemoteService;
import com.example.chatEats.factory.persistence.Account;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;

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
                    //获取我的信息
                    User user = accountRspModel.getUser();
                    //第一种
                    user.save();
                        /* 第二种通过ModelAdapter
                        FlowManager.getModelAdapter(User.class).save(user);
                        //3
                        DatabaseDefinition definition = FlowManager.getDatabase(AppDatabase.class);
                        definition.beginTransactionAsync(new ITransaction() {
                            @Override
                            public void execute(DatabaseWrapper databaseWrapper) {
                                FlowManager.getModelAdapter(User.class).save(user);
                            }
                        }).build().execute();*/

                    Account.login(accountRspModel);


                    if (accountRspModel.isBind()) {
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
