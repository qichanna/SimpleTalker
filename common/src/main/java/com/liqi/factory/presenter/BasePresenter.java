package com.liqi.factory.presenter;

/**
 * Created by liqi7 on 2017/8/21.
 */

public class BasePresenter<T extends BaseContract.View> implements BaseContract.Presenter{
    private T mView;

    public BasePresenter(T view) {
        setView(view);
    }

    /**
     * 设置一个View，子类可以复写
     * @param view
     */
    protected void setView(T view){
        this.mView = view;
        this.mView.setPresenter(this);
    }

    /**
     * 给子类使用的获取View的操作
     * @return
     */
    protected final T getView(){
        return mView;
    }

    @Override
    public void start() {
        // 开始的时候进行Loading调用
        T view = mView;
        if(view != null){
            view.showLoading();
        }
    }

    @Override
    public void destroy() {
        T view = mView;
        mView = null;
        if(view != null){
            // 把Presenter设置为NULL
            view.setPresenter(null);
        }
    }
}
