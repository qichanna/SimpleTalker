package com.liqi.simpletalker.frags.account;


import android.content.Context;

import com.liqi.common.app.Fragment;
import com.liqi.common.app.PresenterFragment;
import com.liqi.simpletalker.R;
import com.liqi.talker.factory.presenter.account.LoginContract;

/**
 *  登录
 */
public class LoginFragment extends PresenterFragment<LoginContract.Presenter>
        implements LoginContract.View{
    private AccountTriggrt mAccountTriggrt;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 拿到我们的Activity的引用
        mAccountTriggrt = (AccountTriggrt) context;
    }

    @Override
    protected LoginContract.Presenter initPresenter() {
        return null;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onResume() {
        super.onResume();

        // 进行一次切换，默认切换为注册界面
        mAccountTriggrt.triggerView();
    }

    @Override
    public void loginSuccess() {

    }
}
