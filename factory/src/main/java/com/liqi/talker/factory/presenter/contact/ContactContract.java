package com.liqi.talker.factory.presenter.contact;

import com.liqi.common.widget.recycler.RecyclerAdapter;
import com.liqi.factory.presenter.BaseContract;
import com.liqi.talker.factory.model.db.User;

/**
 * Created by liqi on 2017/9/20.
 */

public interface ContactContract {
    // 什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter{

    }

    // 都在基类完成了
    interface View extends BaseContract.RecyclerView<Presenter, User>{

    }

}
