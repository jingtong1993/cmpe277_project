package com.example.chatEats.push.frags.message;


import com.example.chatEats.factory.model.db.Group;
import com.example.chatEats.factory.presenter.message.ChatContract;
import com.example.chatEats.push.R;

/**
 * 群聊天界面实现
 */
public class ChatGroupFragment extends ChatFragment<Group>
        implements ChatContract.GroupView {


    public ChatGroupFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_chat_group;
    }

    @Override
    protected ChatContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onInit(Group group) {

    }
}
