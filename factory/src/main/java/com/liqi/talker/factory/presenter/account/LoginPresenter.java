package com.liqi.talker.factory.presenter.account;

import android.support.annotation.StringRes;

import com.liqi.factory.data.DataSource;
import com.liqi.factory.presenter.BasePresenter;
import com.liqi.talker.factory.model.db.User;

/**
 * Created by liqi7 on 2017/8/23.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View>
        implements LoginContract.Presenter, DataSource.Callback<User> {
    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public void login(String phone, String password) {

    }

    @Override
    public void onDataLoaded(User user) {

    }

    @Override
    public void onDataNotAvailable(@StringRes int strRes) {

    }
}
