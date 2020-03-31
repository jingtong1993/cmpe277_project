package com.example.chatEats.push.activities;

import android.content.Intent;

import com.example.chatEats.common.app.Activity;
import com.example.chatEats.common.app.Fragment;
import com.example.chatEats.push.R;
import com.example.chatEats.push.frags.user.UpdateInfoFragment;

public class UserActivity extends Activity {
    private Fragment mCurFragment;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        mCurFragment = new UpdateInfoFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.lay_container, mCurFragment)
                .commit();
    }

    // Activity中收到剪切图片成功的回调
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        mCurFragment.onActivityResult(requestCode, resultCode, data);
    }
}
