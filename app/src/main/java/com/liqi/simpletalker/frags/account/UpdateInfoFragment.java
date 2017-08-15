package com.liqi.simpletalker.frags.account;


import com.liqi.common.app.Fragment;
import com.liqi.common.widget.PortraitView;
import com.liqi.simpletalker.R;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdateInfoFragment extends Fragment {
    @BindView(R.id.im_portrait)
    PortraitView mPortrait;

    public UpdateInfoFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_update_info;
    }

    @OnClick(R.id.im_portrait)
    void onPortraitClick(){

    }
}
