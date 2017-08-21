package com.liqi.factory.presenter;

import android.support.annotation.StringRes;

/**
 * Created by liqi7 on 2017/8/21.
 */

public interface BaseContract {
    interface View<T extends Presenter>{
        // 共用的: 显示一个字符串错误
        void showError(@StringRes int str);

        //共共的: 显示进度条
        void showLoading();

        //支持设置一个Presenter
        void setPresenter(T presenter);
    }


    interface Presenter{
        // 共用的开始触发
        void start();

        // 共用的销毁触发
        void destroy();
    }
}
