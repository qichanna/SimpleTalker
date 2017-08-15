package com.liqi.simpletalker.frags.main;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liqi.common.app.Fragment;
import com.liqi.common.widget.GalleyView;
import com.liqi.simpletalker.R;

import butterknife.BindView;


public class ActiveFragment extends Fragment {
    @BindView(R.id.galleyView)
    GalleyView mgalley;


    public ActiveFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_active;
    }

    @Override
    protected void initData() {
        super.initData();

        mgalley.setup(getLoaderManager(), new GalleyView.SelectedChangeListener() {
            @Override
            public void onSelectedCountChanged(int count) {

            }
        });
    }
}
