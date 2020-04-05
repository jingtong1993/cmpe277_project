package com.example.chatEats.factory.presenter.account;

import com.example.chatEats.factory.presenter.BasePresenter;

public class LoginPresenter extends BasePresenter<com.example.chatEats.factory.presenter.persenter.LoginContract.View> implements com.example.chatEats.factory.presenter.persenter.LoginContract.Presenter {


    public LoginPresenter(com.example.chatEats.factory.presenter.persenter.LoginContract.View view) {
        super(view);
    }

    @Override
    public void login(String phone, String password) {

    }
}
