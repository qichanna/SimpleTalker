package com.liqi.simpletalker.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liqi.common.app.Activity;
import com.liqi.common.app.Fragment;
import com.liqi.simpletalker.R;
import com.liqi.simpletalker.frags.account.UpdateInfoFragment;
import com.yalantis.ucrop.UCrop;

public class AccountActivity extends Activity {
    private Fragment mCurFragment;

    /**
     * 账户Activity显示的入口
     * @param context
     */
    public static void show(Context context){
        context.startActivity(new Intent(context,AccountActivity.class));
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_account;
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
