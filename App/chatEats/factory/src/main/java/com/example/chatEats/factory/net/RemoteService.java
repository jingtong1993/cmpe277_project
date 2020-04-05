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
//
//    // 用户搜索的接口
//    @GET("user/search/{name}")
//    Call<RspModel<List<UserCard>>> userSearch(@Path("name") String name);
//
//    // 用户关注接口
//    @PUT("user/follow/{userId}")
//    Call<RspModel<UserCard>> userFollow(@Path("userId") String userId);
//
//    // 获取联系人列表
//    @GET("user/contact")
//    Call<RspModel<List<UserCard>>> userContacts();
//
//    // 查询某人的信息
//    @GET("user/{userId}")
//    Call<RspModel<UserCard>> userFind(@Path("userId") String userId);
//
//    // 发送消息的接口
//    @POST("msg")
//    Call<RspModel<MessageCard>> msgPush(@Body MsgCreateModel model);
//
//    // 创建群
//    @POST("group")
//    Call<RspModel<GroupCard>> groupCreate(@Body GroupCreateModel model);
//
//    // 拉取群信息
//    @GET("group/{groupId}")
//    Call<RspModel<GroupCard>> groupFind(@Path("groupId") String groupId);
//
//    // 群搜索的接口
//    @GET("group/search/{name}")
//    Call<RspModel<List<GroupCard>>> groupSearch(@Path(value = "name", encoded = true) String name);
//
//    // 我的群列表
//    @GET("group/list/{date}")
//    Call<RspModel<List<GroupCard>>> groups(@Path(value = "date", encoded = true) String date);
//
//    // 我的群的成员列表
//    @GET("group/{groupId}/member")
//    Call<RspModel<List<GroupMemberCard>>> groupMembers(@Path("groupId") String groupId);
//
//    // 给群添加成员
//    @POST("group/{groupId}/member")
//    Call<RspModel<List<GroupMemberCard>>> groupMemberAdd(@Path("groupId") String groupId,
//                                                         @Body GroupMemberAddModel model);
//

}

