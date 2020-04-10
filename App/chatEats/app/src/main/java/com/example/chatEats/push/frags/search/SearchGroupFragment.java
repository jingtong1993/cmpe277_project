package com.example.chatEats.push.frags.search;


import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chatEats.common.app.Fragment;
import com.example.chatEats.push.R;
import com.example.chatEats.push.activities.SearchActivity;

public class SearchGroupFragment extends Fragment implements SearchActivity.SearchFragment{


    public SearchGroupFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_search_group;
    }

    @Override
    public void search(String content) {

    }
}
