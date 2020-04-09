package com.example.chatEats.factory.net;

import com.example.chatEats.factory.model.api.RspModel;
import com.example.chatEats.factory.model.api.account.AccountRspModel;
import com.example.chatEats.factory.model.api.account.LoginModel;
import com.example.chatEats.factory.model.api.account.RegisterModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RemoteService {


    @POST("account/register")
    Call<RspModel<AccountRspModel>> accountRegister(@Body RegisterModel model);


    @POST("account/login")
    Call<RspModel<AccountRspModel>> accountLogin(@Body LoginModel model);


    @POST("account/bind/{pushId}")
    Call<RspModel<AccountRspModel>> accountBind(@Path(encoded = true, value = "pushId") String pushId);

//    // 用户更新的接口
//    @PUT("user")
//    Call<RspModel<UserCard>> userUpdate(@Body UserUpdateModel model);

}

