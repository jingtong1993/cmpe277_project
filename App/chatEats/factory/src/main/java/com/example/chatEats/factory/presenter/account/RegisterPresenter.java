package com.example.chatEats.factory.presenter.account;

import android.text.TextUtils;

import com.example.chatEats.common.Common;
import com.example.chatEats.factory.data.helper.AccountHelper;
import com.example.chatEats.factory.model.api.RegisterModel;
import com.example.chatEats.factory.presenter.BasePresenter;

import java.util.regex.Pattern;

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    public RegisterPresenter(RegisterContract.View view) {
        super(view);
    }

    @Override
    public void register(String phone, String name, String password) {
        start();

        // 得到View接口
        RegisterContract.View view = getView();

        // 校验
        if (!checkMobile(phone)) {
            // 提示
            //view.showError(R.string.data_account_register_invalid_parameter_mobile);
        } else if (name.length() < 2) {
            // 姓名需要大于2位
            //view.showError(R.string.data_account_register_invalid_parameter_name);
        } else if (password.length() < 6) {
            // 密码需要大于6位
            //view.showError(R.string.data_account_register_invalid_parameter_password);
        } else {
            // 进行网络请求
            // 构造Model，进行请求调用
            RegisterModel model = new RegisterModel(phone, password, name);
            // 进行网络请求，并设置回送接口为自己
            AccountHelper.register(model);
        }
    }

    @Override
    public boolean checkMobile(String phone) {
        return !TextUtils.isEmpty(phone)
                && Pattern.matches(Common.Constance.REGEX_MOBILE, phone);
    }

}