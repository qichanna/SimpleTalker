package com.liqi.common.app;

import com.liqi.factory.presenter.BaseContract;

/**
 * Created by liqi7 on 2017/9/1.
 */

public abstract class PresenterToolbarActivity<Presenter extends BaseContract.Presenter>
        extends ToolbarActivity implements BaseContract.View<Presenter>{
    protected Presenter mPresenter;

    @Override
    protected void initBefore() {
        super.initBefore();
        // 初始化Presenter
        initPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 界面关闭时进行销毁的操作
        if(mPresenter != null){
            mPresenter.destroy();
        }
    }

    /**
     *  初始化Presenter
     * @return
     */
    protected abstract Presenter initPresenter();

    @Override
    public void showError(int str) {
        // 显示错误, 优先使用占位布局
        if (mPlaceHolderView != null) {
            mPlaceHolderView.triggerError(str);
        } else {
            Application.showToast(str);
        }
    }

    @Override
    public void showLoading() {
        if (mPlaceHolderView != null) {
            mPlaceHolderView.triggerLoading();
        }
    }

    protected void hideLoading(){
        if(mPlaceHolderView != null){
            mPlaceHolderView.triggerOk();
        }
    }

    @Override
    public void setPresenter(Presenter presenter) {
        // View中赋值Presenter
        mPresenter = presenter;
    }
}
