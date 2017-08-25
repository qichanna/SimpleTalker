package com.liqi.talker.factory.presenter.account;

import android.support.annotation.StringRes;
import android.text.TextUtils;

import com.liqi.factory.data.DataSource;
import com.liqi.factory.presenter.BasePresenter;
import com.liqi.talker.factory.R;
import com.liqi.talker.factory.data.helper.AccountHelper;
import com.liqi.talker.factory.model.api.account.LoginModel;
import com.liqi.talker.factory.model.db.User;
import com.liqi.talker.factory.persistence.Account;

import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

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
        start();

        final LoginContract.View view = getView();

        if(TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)){
            view.showError(R.string.data_account_login_invalid_parameter);
        }else {
            // 尝试传递PushId
            LoginModel model = new LoginModel(phone,password, Account.getPushId());
            AccountHelper.login(model,this);
        }
    }

    @Override
    public void onDataLoaded(User user) {
        final LoginContract.View view = getView();
        if(view == null){
            return;
        }
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                view.loginSuccess();
            }
        });
    }

    @Override
    public void onDataNotAvailable(@StringRes final int strRes) {
        // 网络请求告知注册失败
        final LoginContract.View view = getView();
        if(view == null){
            return;
        }
        // 此时是从网络回送回来的，并不保证处于主线程状态
        // 强制执行在主线程中
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                // 调用主界面注册失败显示错误
                view.showError(strRes);
            }
        });
    }
}
