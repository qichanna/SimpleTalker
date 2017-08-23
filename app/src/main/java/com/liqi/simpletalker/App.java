package com.liqi.simpletalker;

import com.igexin.sdk.PushManager;
import com.liqi.common.app.Application;
import com.liqi.talker.factory.Factory;

/**
 * Created by liqi7 on 2017/8/16.
 */

public class App  extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        // 调用Factory进行初始化
        Factory.setup();
        // 推送进行初始化
        PushManager.getInstance().initialize(this);
    }
}
