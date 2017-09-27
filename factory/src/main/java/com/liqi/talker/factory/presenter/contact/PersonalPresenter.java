package com.liqi.talker.factory.presenter.contact;

import com.liqi.factory.presenter.BasePresenter;
import com.liqi.talker.factory.Factory;
import com.liqi.talker.factory.data.helper.UserHelper;
import com.liqi.talker.factory.model.db.User;
import com.liqi.talker.factory.presenter.account.RegisterContract;

/**
 * Created by liqi on 2017/9/27.
 */

public class PersonalPresenter extends BasePresenter<PersonalContract.View>
        implements PersonalContract.Presenter{

    private User user;

    public PersonalPresenter(PersonalContract.View view) {
        super(view);
    }

    @Override
    public void start() {
        super.start();



        // 个人界面用户数据优先从网络拉取
        Factory.runOnAsync(new Runnable() {
            @Override
            public void run() {
                PersonalContract.View view = getView();
                if(view == null){
                    String id = getView().getUserId();
                    User user = UserHelper.searchFirstOfNet(id);
                    getView().onLoadDone(user);
                }
            }
        });
    }

    @Override
    public User getUserPersonal() {
        return null;
    }
}
