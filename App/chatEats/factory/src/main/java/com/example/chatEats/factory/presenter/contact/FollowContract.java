package com.example.chatEats.factory.presenter.contact;

import com.example.chatEats.factory.model.card.UserCard;
import com.example.chatEats.factory.presenter.BaseContract;

public interface FollowContract {
    interface Presenter extends BaseContract.Presenter {
        // 关注一个人
        void follow(String id);
    }

    interface View extends BaseContract.View<Presenter> {
        // 成功的情况下返回一个用户的信息
        void onFollowSucceed(UserCard userCard);
    }
}
