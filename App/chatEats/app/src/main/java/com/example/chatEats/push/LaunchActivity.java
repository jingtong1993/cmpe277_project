package com.example.chatEats.push;

import com.example.chatEats.common.app.Activity;
import com.example.chatEats.push.activities.MainActivity;
import com.example.chatEats.push.frags.assist.PermissionsFragment;

public class LaunchActivity extends Activity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_launch;
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (PermissionsFragment.haveAll(this, getSupportFragmentManager())) {
            MainActivity.show(this);
            finish();
        }

    }
}
