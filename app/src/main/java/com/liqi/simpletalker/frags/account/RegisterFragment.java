package com.liqi.simpletalker.frags.account;


import android.content.Context;

import com.liqi.common.app.Fragment;
import com.liqi.simpletalker.R;

/**
 * 注册的界面
 */
public class RegisterFragment extends Fragment {
    private AccountTriggrt mAccountTriggrt;

    public RegisterFragment() {
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
        return R.layout.fragment_register;
    }
}
