package com.example.chatEats.factory.presenter.message;



import com.example.chatEats.factory.data.helper.MessageHelper;
import com.example.chatEats.factory.data.message.MessageDataSource;
import com.example.chatEats.factory.model.api.message.MsgCreateModel;
import com.example.chatEats.factory.model.db.Message;
import com.example.chatEats.factory.persistence.Account;
import com.example.chatEats.factory.presenter.BaseSourcePresenter;
import com.example.chatEats.factory.utils.DiffUiDataCallback;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DiffUtil;


@SuppressWarnings("WeakerAccess")
public class ChatPresenter<View extends ChatContract.View>
        extends BaseSourcePresenter<Message, Message, MessageDataSource, View>
        implements ChatContract.Presenter {

    // 接收者Id，可能是群，或者人的ID
    protected String mReceiverId;
    // 区分是人还是群Id
    protected int mReceiverType;


    public ChatPresenter(MessageDataSource source, View view,
                         String receiverId, int receiverType) {
        super(source, view);
        this.mReceiverId = receiverId;
        this.mReceiverType = receiverType;
    }

    @Override
    public void pushText(String content) {
        // 构建一个新的消息
        MsgCreateModel model = new MsgCreateModel.Builder()
                .receiver(mReceiverId, mReceiverType)
                .content(content, Message.TYPE_STR)
                .build();

        // 进行网络发送
        MessageHelper.push(model);
    }

    @Override
    public void pushAudio(String path) {
        // TODO 发送语音
    }

    @Override
    public void pushImages(String[] paths) {
        // TODO 发送图片
    }

    @Override
    public boolean rePush(Message message) {
        // 确定消息是可重复发送的
        if (Account.getUserId().equalsIgnoreCase(message.getSender().getId())
                && message.getStatus() == Message.STATUS_FAILED) {

            // 更改状态
            message.setStatus(Message.STATUS_CREATED);
            // 构建发送Model
            MsgCreateModel model = MsgCreateModel.buildWithMessage(message);
            MessageHelper.push(model);
            return true;
        }

        return false;
    }

    @Override
    public void onDataLoaded(List<Message> messages) {
        ChatContract.View view = getView();
        if (view == null)
            return;

        // 拿到老数据
        @SuppressWarnings("unchecked")
        List<Message> old = view.getRecyclerAdapter().getItems();

//        List<Message> temp1 = new ArrayList<>();
//        for (int i = 0; i < messages.size(); i++) {
//            if (messages.get(i).getStatus() == 0) {
//                temp1.add(messages.get(i));
//            }
//        }
//
//        messages = temp1;
//
//        List<Message> temp2 = new ArrayList<>();
//        for (int i = 0; i < old.size(); i++) {
//            if (old.get(i).getStatus() == 0) {
//                temp2.add(old.get(i));
//            }
//        }
//
//        old = temp2;


        // 差异计算
        DiffUiDataCallback<Message> callback = new DiffUiDataCallback<>(old, messages);
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);

        // 进行界面刷新
        refreshData(result, messages);
    }
}
