package com.liqi.talker.factory.presenter.contact;

import com.liqi.factory.presenter.BasePresenter;

/** 联系人的Presenter实现
 * Created by liqi on 2017/9/20.
 */

public class ContactPresenter extends BasePresenter<ContactContract.View>
        implements ContactContract.Presenter{
    public ContactPresenter(ContactContract.View view) {
        super(view);
    }

    @Override
    public void start() {
        super.start();

        // TODO 加载数据
    }
}
