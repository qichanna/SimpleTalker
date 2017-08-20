package com.liqi.simpletalker.frags.account;


import android.content.Context;

import com.liqi.common.app.Fragment;
import com.liqi.simpletalker.R;

/**
 *  登录
 */
public class LoginFragment extends Fragment {
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
    protected int getContentLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onResume() {
        super.onResume();

        // 进行一次切换，默认切换为注册界面
        mAccountTriggrt.triggerView();
    }
}
