package com.liqi.factory.presenter;

import android.support.annotation.StringRes;

import com.liqi.common.widget.recycler.RecyclerAdapter;

/**
 * Created by liqi7 on 2017/8/21.
 */

public interface BaseContract {
    // 基本的界面职责
    interface View<T extends Presenter>{
        // 共用的: 显示一个字符串错误
        void showError(@StringRes int str);

        //共共的: 显示进度条
        void showLoading();

        //支持设置一个Presenter
        void setPresenter(T presenter);
    }

    // 基本的Presenter职责
    interface Presenter{
        // 共用的开始触发
        void start();

        // 共用的销毁触发
        void destroy();
    }

    interface RecyclerView<T extends Presenter,ViewModel> extends View<T>{
        // 界面端只能刷新整个数据集合，不能精确到每一条数据更新
        // void OnDone(List<User> users);

        // 拿到一个适配器，然后自己自主的进行刷新
        RecyclerAdapter<ViewModel> getRecyclerAdapter();

        // 当适配器数据更改； 的时候触发
        void onAdapterDataChanged();

    }
}
