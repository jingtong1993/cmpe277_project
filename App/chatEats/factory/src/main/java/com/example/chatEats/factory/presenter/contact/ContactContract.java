package com.example.chatEats.factory.presenter.contact;

import com.example.chatEats.common.widget.recycler.RecyclerAdapter;
import com.example.chatEats.factory.model.db.User;
import com.example.chatEats.factory.presenter.BaseContract;

import java.util.List;

public interface ContactContract {
    // 什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter {

    }

    // 都在基类完成了
    interface View extends BaseContract.RecyclerView<Presenter, User> {
    }
}
