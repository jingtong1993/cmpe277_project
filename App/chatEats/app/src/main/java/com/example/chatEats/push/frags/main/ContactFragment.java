package com.example.chatEats.push.frags.main;


import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chatEats.common.app.PresenterFragment;
import com.example.chatEats.common.widget.EmptyView;
import com.example.chatEats.common.widget.PortraitView;
import com.example.chatEats.common.widget.recycler.RecyclerAdapter;
import com.example.chatEats.factory.model.card.UserCard;
import com.example.chatEats.factory.model.db.User;
import com.example.chatEats.factory.presenter.contact.ContactContract;
import com.example.chatEats.factory.presenter.contact.ContactPresenter;
import com.example.chatEats.push.R;
import com.example.chatEats.push.activities.MessageActivity;
import com.example.chatEats.push.activities.PersonalActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ContactFragment extends PresenterFragment<ContactContract.Presenter>
        implements ContactContract.View {

    @BindView(R.id.empty)
    EmptyView mEmptyView;

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    // 适配器，User，可以直接从数据库查询数据
    private RecyclerAdapter<User> mAdapter;

    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);

        // 初始化Recycler
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.setAdapter(mAdapter = new RecyclerAdapter<User>() {
            @Override
            protected int getItemViewType(int position, User userCard) {
                // 返回cell的布局id
                return R.layout.cell_contact_list;
            }

            @Override
            protected ViewHolder<User> onCreateViewHolder(View root, int viewType) {
                return new ContactFragment.ViewHolder(root);
            }

        });

        // 点击事件监听
        mAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<User>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, User user) {
                // 跳转到聊天界面
                MessageActivity.show(getContext(), user);
            }
        });

        // 初始化占位布局
        mEmptyView.bind(mRecycler);
        setPlaceHolderView(mEmptyView);

    }

    @Override
    protected void onFirstInit() {
        super.onFirstInit();
        // 进行一次数据加载
        mPresenter.start();
    }

    @Override
    protected ContactContract.Presenter initPresenter() {
        // 初始化Presenter
        return new ContactPresenter(this);
    }

    @Override
    public RecyclerAdapter<User> getRecyclerAdapter() {
        return mAdapter;
    }

    @Override
    public void onAdapterDataChanged() {
        // 进行界面操作
        mPlaceHolderView.triggerOkOrEmpty(mAdapter.getItemCount() > 0);
    }


    class ViewHolder extends RecyclerAdapter.ViewHolder<User> {
        @BindView(R.id.im_portrait)
        PortraitView mPortraitView;

        @BindView(R.id.txt_name)
        TextView mName;

        @BindView(R.id.txt_desc)
        TextView mDesc;


        public ViewHolder(View itemView) {
            super(itemView);
        }

        //ContactFragment.this
        @Override
        protected void onBind(User user) {
            mPortraitView.setup(Glide.with(getContext()), user);
            mName.setText(user.getName());
            mDesc.setText(user.getDesc());
        }

        @OnClick(R.id.im_portrait)
        void onPortraitClick() {
            // 显示信息
            PersonalActivity.show(getContext(), mData.getId());
        }
    }
}
