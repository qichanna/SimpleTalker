package com.liqi.simpletalker.frags.main;


import com.liqi.common.app.Fragment;
import com.liqi.common.widget.GalleryView;
import com.liqi.simpletalker.R;

import butterknife.BindView;


public class ActiveFragment extends Fragment {
//    @BindView(R.id.galleryView)
//    GalleryView mgalley;


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

//        mgalley.setup(getLoaderManager(), new GalleryView.SelectedChangeListener() {
//            @Override
//            public void onSelectedCountChanged(int count) {
//
//            }
//        });
    }
}
