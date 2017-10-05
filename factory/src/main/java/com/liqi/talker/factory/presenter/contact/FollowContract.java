package com.liqi.talker.factory.presenter.contact;

import com.liqi.factory.presenter.BaseContract;
import com.liqi.talker.factory.model.card.UserCard;

/**
 * Created by liqi on 2017/9/17.
 */

public interface FollowContract {
    // 任务调度者
    interface Presenter extends BaseContract.Presenter {
        // 关注一个人
        void follow(String id);
    }

    interface View extends BaseContract.View<Presenter>{
        // 成功的情况下返回一个用户的信息
        void onFollowSucceed(UserCard userCard);
    }
}
