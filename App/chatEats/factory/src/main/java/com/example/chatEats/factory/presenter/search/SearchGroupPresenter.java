package com.example.chatEats.factory.presenter.search;

import com.example.chatEats.factory.presenter.BasePresenter;

public class SearchGroupPresenter extends BasePresenter<SearchContract.GroupView>
        implements SearchContract.Presenter {
    public SearchGroupPresenter(SearchContract.GroupView view) {
        super(view);
    }

    @Override
    public void search(String content) {

    }
}

