package com.example.chatEats.factory.net;

import com.example.chatEats.factory.model.api.RspModel;
import com.example.chatEats.factory.model.api.account.AccountRspModel;
import com.example.chatEats.factory.model.api.account.LoginModel;
import com.example.chatEats.factory.model.api.account.RegisterModel;
import com.example.chatEats.factory.model.api.message.MsgCreateModel;
import com.example.chatEats.factory.model.api.user.UserUpdateModel;
import com.example.chatEats.factory.model.card.MessageCard;
import com.example.chatEats.factory.model.card.UserCard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RemoteService {


    @POST("account/register")
    Call<RspModel<AccountRspModel>> accountRegister(@Body RegisterModel model);


    @POST("account/login")
    Call<RspModel<AccountRspModel>> accountLogin(@Body LoginModel model);


    @POST("account/bind/{pushId}")
    Call<RspModel<AccountRspModel>> accountBind(@Path(encoded = true, value = "pushId") String pushId);

    // 用户更新的接口
    @PUT("user")
    Call<RspModel<UserCard>> userUpdate(@Body UserUpdateModel model);

    // 用户搜索的接口
    @GET("user/search/{name}")
    Call<RspModel<List<UserCard>>> userSearch(@Path("name") String name);

    // 用户关注接口
    @PUT("user/follow/{userId}")
    Call<RspModel<UserCard>> userFollow(@Path("userId") String userId);

    // 获取联系人列表
    @GET("user/contact")
    Call<RspModel<List<UserCard>>> userContacts();

    @GET("user/{userId}")
    Call<RspModel<UserCard>> userFind(@Path("userId") String userId);

    // 发送消息的接口
    @POST("msg")
    Call<RspModel<MessageCard>> msgPush(@Body MsgCreateModel model);

}

