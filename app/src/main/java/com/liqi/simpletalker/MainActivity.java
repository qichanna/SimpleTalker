package com.liqi.simpletalker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.liqi.common.app.Activity;

import butterknife.BindView;

public class MainActivity extends Activity {

    @BindView(R.id.txt_result)
    TextView mResult;


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();
        mResult.setText("hi ni hao");
    }
}
