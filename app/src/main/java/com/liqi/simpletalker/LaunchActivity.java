package com.liqi.simpletalker;

import android.os.Bundle;
import android.app.Activity;

import com.liqi.simpletalker.activities.MainActivity;
import com.liqi.simpletalker.frags.assist.PermissionsFragment;

public class LaunchActivity extends com.liqi.common.app.Activity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(PermissionsFragment.haveAll(this,getSupportFragmentManager())){
            MainActivity.show(this);
            finish();
        }
    }
}
