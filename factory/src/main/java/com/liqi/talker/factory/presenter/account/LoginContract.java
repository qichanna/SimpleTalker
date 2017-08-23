package com.liqi.talker.factory.presenter.account;

import com.liqi.factory.presenter.BaseContract;

/**
 * Created by liqi on 2017/8/21.
 */

public interface LoginContract {
    interface View extends BaseContract.View<Presenter>{
        // 登录成功
        void loginSuccess();
    }

    interface Presenter extends BaseContract.Presenter{
        // 发起一个登录
        void login(String phone, String password);
    }
}
