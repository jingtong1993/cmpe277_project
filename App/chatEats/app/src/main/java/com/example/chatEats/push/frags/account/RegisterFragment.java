package com.example.chatEats.push.frags.account;


import android.content.Context;

import com.example.chatEats.common.app.Fragment;
import com.example.chatEats.common.app.PresenterFragment;
import com.example.chatEats.factory.presenter.account.RegisterContract;
import com.example.chatEats.factory.presenter.account.RegisterPresenter;
import com.example.chatEats.push.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends PresenterFragment<RegisterContract.Presenter> implements RegisterContract.View {

    private AccountTrigger mAccountTrigger;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAccountTrigger = (AccountTrigger) context;
    }

    @Override
    protected RegisterContract.Presenter initPresenter() {
        return new RegisterPresenter();
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    public void registerSuccess() {

    }

    @Override
    public void showError(int str) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {

    }
}
