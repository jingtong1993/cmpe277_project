package com.example.chatEats.factory.presenter.message;

import com.example.chatEats.factory.data.helper.UserHelper;
import com.example.chatEats.factory.data.message.MessageRepository;
import com.example.chatEats.factory.model.db.Message;
import com.example.chatEats.factory.model.db.User;


public class ChatUserPresenter extends ChatPresenter<ChatContract.UserView>
        implements ChatContract.Presenter {

    public ChatUserPresenter(ChatContract.UserView view, String receiverId) {
        // 数据源，View，接收者，接收者的类型
        super(new MessageRepository(receiverId), view, receiverId, Message.RECEIVER_TYPE_NONE);


    }

    @Override
    public void start() {
        super.start();

        // 从本地拿这个人的信息
        User receiver = UserHelper.findFromLocal(mReceiverId);
        getView().onInit(receiver);
    }
}
