package com.liqi.simpletalker.activities;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;

import com.liqi.factory.model.Author;
import com.liqi.simpletalker.R;

public class MessageActivity extends Activity {

    /**
     *  显示人的聊天界面
     * @param context 上下文信息
     * @param author 人的信息
     */
    public static void show(Context context, Author author){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
    }

}
