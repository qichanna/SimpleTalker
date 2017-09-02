package com.liqi.simpletalker.frags.search;

import com.liqi.common.app.Fragment;
import com.liqi.common.app.PresenterFragment;
import com.liqi.simpletalker.R;
import com.liqi.simpletalker.activities.SearchActivity;
import com.liqi.talker.factory.model.UserCard.UserCard;
import com.liqi.talker.factory.presenter.search.SearchContract;
import com.liqi.talker.factory.presenter.search.SearchUserPresenter;

import java.util.List;

/**
 * 搜索群的界面实现
 */
public class SearchUserFragment extends PresenterFragment<SearchContract.Presenter>
        implements SearchActivity.SearchFragment, SearchContract.UserView {


    public SearchUserFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_search_user;
    }

    @Override
    public void search(String content) {
        // Activity -> Fragment -> Presenter -> Net
        mPresenter.search(content);
    }

    @Override
    public void onSearchDone(List<UserCard> userCards) {
        // 数据成功的情况下返回数据
    }

    @Override
    protected SearchContract.Presenter initPresenter() {
        // 初始化presenter
        return new SearchUserPresenter(this);
    }
}
