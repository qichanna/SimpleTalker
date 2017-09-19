package com.liqi.simpletalker.frags.search;

import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.liqi.common.app.Fragment;
import com.liqi.common.app.PresenterFragment;
import com.liqi.common.widget.EmptyView;
import com.liqi.common.widget.PortraitView;
import com.liqi.common.widget.recycler.RecyclerAdapter;
import com.liqi.factory.presenter.BaseContract;
import com.liqi.simpletalker.R;
import com.liqi.simpletalker.activities.SearchActivity;
import com.liqi.talker.factory.model.UserCard.UserCard;
import com.liqi.talker.factory.presenter.contact.FollowContract;
import com.liqi.talker.factory.presenter.contact.FollowPresenter;
import com.liqi.talker.factory.presenter.search.SearchContract;
import com.liqi.talker.factory.presenter.search.SearchUserPresenter;

import net.qiujuer.genius.ui.Ui;
import net.qiujuer.genius.ui.compat.UiCompat;
import net.qiujuer.genius.ui.drawable.LoadingCircleDrawable;
import net.qiujuer.genius.ui.drawable.LoadingDrawable;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 搜索群的界面实现
 */
public class SearchUserFragment extends PresenterFragment<SearchContract.Presenter>
        implements SearchActivity.SearchFragment, SearchContract.UserView {


    @BindView(R.id.empty)
    EmptyView mEmptyView;

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    private RecyclerAdapter<UserCard> mAdapter;

    public SearchUserFragment() {

    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_search_user;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);

        // 初始化Recycler
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.setAdapter(mAdapter = new RecyclerAdapter<UserCard>() {
            @Override
            protected int getItemType(int position, UserCard userCard) {
                // 返回cell的布局id
                return R.layout.cell_search_list;
            }

            @Override
            protected ViewHolder<UserCard> onCreateViewHolder(View root, int viewType) {
                return new SearchUserFragment.ViewHolder(root);
            }
        });

        // 初始化占位布局
        mEmptyView.bind(mRecycler);
        setPlaceHolderView(mEmptyView);
    }

    @Override
    protected void initData() {
        super.initData();
        search("");
    }

    @Override
    public void search(String content) {
        // Activity -> Fragment -> Presenter -> Net
        mPresenter.search(content);
    }

    @Override
    public void onSearchDone(List<UserCard> userCards) {
        // 数据成功的情况下返回数据
        mAdapter.replace(userCards);
        // 如果有数据，则是OK，没有数据就显示空布局
        mPlaceHolderView.triggerOkOrEmpty(mAdapter.getItemCount() > 0);
    }

    @Override
    protected SearchContract.Presenter initPresenter() {
        // 初始化presenter
        return new SearchUserPresenter(this);
    }

    /**
     * 每一个cell的布局操作
     */
    class ViewHolder extends RecyclerAdapter.ViewHolder<UserCard>
            implements FollowContract.View{
        @BindView(R.id.im_portrait)
        PortraitView mPortraitView;

        @BindView(R.id.txt_name)
        TextView mName;

        @BindView(R.id.im_follow)
        ImageView mFollow;

        private FollowContract.Presenter mPresenter;

        public ViewHolder(View itemView) {
            super(itemView);
            // 当前View和绑定
            new FollowPresenter(this);
        }

        @Override
        protected void onBind(UserCard userCard) {
            mPortraitView.setup(Glide.with(SearchUserFragment.this),userCard);
            mName.setText(userCard.getName());
            mFollow.setEnabled(!userCard.isFollow());
        }

        @OnClick(R.id.im_follow)
        void onFollowCLick(){
            // 发起关注
            String id = mData.getId();
//            Toast.makeText(getActivity(),id,500).show();
            mPresenter.follow(mData.getId());
        }

        @Override
        public void showError(@StringRes int str) {
            // 更新当前界面状态
            if(mFollow.getDrawable() instanceof LoadingCircleDrawable){
                // 失败则停止动画，并且显示一个圆圈
                LoadingCircleDrawable drawable =  (LoadingCircleDrawable) mFollow.getDrawable();
                drawable.setProgress(1);
                drawable.stop();
            }
        }

        @Override
        public void showLoading() {
            int minSize = (int) Ui.dipToPx(getResources(),22);
            int maxSize = (int) Ui.dipToPx(getResources(),30);
            // 初始化一个圆形的动画Drawable
            LoadingDrawable drawable = new LoadingCircleDrawable(minSize,maxSize);
            drawable.setBackgroundColor(0);

            int[] color = new int[]{UiCompat.getColor(getResources(),R.color.white_alpha_208)};
            drawable.setForegroundColor(color);
            // 设置进去
            mFollow.setImageDrawable(drawable);
            // 启动动画
            drawable.start();
        }

        @Override
        public void setPresenter(FollowContract.Presenter presenter) {
            mPresenter = presenter;
        }

        @Override
        public void onFollowSucceed(UserCard userCard) {
            // 更改当前界面状态
            if(mFollow.getDrawable() instanceof LoadingCircleDrawable){
                ((LoadingCircleDrawable) mFollow.getDrawable()).stop();
                // 设置为默认的
                mFollow.setImageResource(R.drawable.sel_opt_done_add);
            }
            // 发起更新
            updateData(userCard);
        }
    }
}
