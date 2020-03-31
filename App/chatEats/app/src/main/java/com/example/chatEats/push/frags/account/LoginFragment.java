package com.example.chatEats.push.frags.account;


import android.content.Context;

import com.example.chatEats.common.app.Fragment;
import com.example.chatEats.push.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private AccountTrigger mAccountTrigger;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAccountTrigger = (AccountTrigger) context;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onResume() {
        super.onResume();
        mAccountTrigger.triggerView();
    }
}
