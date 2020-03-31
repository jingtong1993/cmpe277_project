package com.example.web.chatEats.push.bean.api.account;

import com.example.web.chatEats.push.bean.card.UserCard;
import com.example.web.chatEats.push.bean.db.User;
import com.google.gson.annotations.Expose;

public class AccountRspModel {
    @Expose
    private UserCard user;
    @Expose
    private String account;
    @Expose
    private String token;
    @Expose
    private boolean isBind;

    public AccountRspModel(User user) {
        this(user, false);
    }

    public AccountRspModel(User user, boolean isBind) {
        this.user = new UserCard(user);
        this.account = user.getPhone();
        this.token = user.getToken();
        this.isBind = isBind;
    }

    public UserCard getUser() {
        return user;
    }

    public void setUser(UserCard user) {
        this.user = user;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isBind() {
        return isBind;
    }

    public void setBind(boolean bind) {
        isBind = bind;
    }
}
