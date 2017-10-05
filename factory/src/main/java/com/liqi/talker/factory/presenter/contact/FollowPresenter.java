package com.liqi.talker.factory.presenter.contact;

import android.support.annotation.StringRes;

import com.liqi.factory.data.DataSource;
import com.liqi.factory.presenter.BasePresenter;
import com.liqi.talker.factory.data.helper.UserHelper;
import com.liqi.talker.factory.model.card.UserCard;

import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

/**
 * Created by liqi on 2017/9/17.
 */

public class FollowPresenter extends BasePresenter<FollowContract.View>
        implements FollowContract.Presenter, DataSource.Callback<UserCard>{

    public FollowPresenter(FollowContract.View view) {
        super(view);
    }

    @Override
    public void follow(String id) {
        start();

        UserHelper.follow(id,this);
    }

    @Override
    public void onDataLoaded(final UserCard userCard) {
        // 成功
        final FollowContract.View view = getView();
        if(view != null){
            Run.onUiAsync(new Action() {
                @Override
                public void call() {
                    view.onFollowSucceed(userCard);
                }
            });
        }
    }

    @Override
    public void onDataNotAvailable(@StringRes final int strRes) {
        final FollowContract.View view = getView();
        if(view != null){
            Run.onUiAsync(new Action() {
                @Override
                public void call() {
                    view.showError(strRes);
                }
            });
        }
    }
}
