package com.liqi.talker.factory.presenter.message;

import com.liqi.factory.presenter.BaseContract;
import com.liqi.talker.factory.model.db.Session;

/**
 * @author qiujuer Email:qiujuer@live.cn
 * @version 1.0.0
 */
public interface SessionContract {
    // 什么都不需要额外定义，开始就是调用start即可
    interface Presenter extends BaseContract.Presenter {

    }

    // 都在基类完成了
    interface View extends BaseContract.RecyclerView<Presenter, Session> {

    }
}
