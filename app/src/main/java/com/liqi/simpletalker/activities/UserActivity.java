package com.liqi.simpletalker.activities;

import android.content.Intent;

import com.liqi.common.app.Activity;
import com.liqi.common.app.Fragment;
import com.liqi.simpletalker.R;
import com.liqi.simpletalker.frags.user.UpdateInfoFragment;

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
                .add(R.id.lay_container,mCurFragment)
                .commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCurFragment.onActivityResult(requestCode,resultCode,data);
    }
}
