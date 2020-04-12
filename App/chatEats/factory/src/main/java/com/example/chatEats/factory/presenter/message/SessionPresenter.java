package com.example.chatEats.factory.presenter.message;



import com.example.chatEats.factory.data.message.SessionDataSource;
import com.example.chatEats.factory.data.message.SessionRepository;
import com.example.chatEats.factory.model.db.Session;
import com.example.chatEats.factory.presenter.BaseSourcePresenter;
import com.example.chatEats.factory.utils.DiffUiDataCallback;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

/**
 * 最近聊天列表的Presenter
 *
 * @author qiujuer Email:qiujuer@live.cn
 * @version 1.0.0
 */
public class SessionPresenter extends BaseSourcePresenter<Session, Session,
        SessionDataSource, SessionContract.View> implements SessionContract.Presenter {

    public SessionPresenter(SessionContract.View view) {
        super(new SessionRepository(), view);
    }

    @Override
    public void onDataLoaded(List<Session> sessions) {
        SessionContract.View view = getView();
        if (view == null)
            return;

        // 差异对比
        List<Session> old = view.getRecyclerAdapter().getItems();
        DiffUiDataCallback<Session> callback = new DiffUiDataCallback<>(old, sessions);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);

        // 刷新界面
        refreshData(result, sessions);
    }
}
