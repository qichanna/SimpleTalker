package com.liqi.talker.factory.data.user;

import com.liqi.factory.data.DataSource;
import com.liqi.talker.factory.model.db.User;

import java.util.List;

/** 联系人数据源
 * Created by liqi on 2017/10/8.
 */

public interface ContactDataSource {
    /**
     *  对数据进行加载的一个职责
     * @param callback
     */
    void load(DataSource.SucceedCallback<List<User>> callback);

    /**
     *  销毁操作
     */
    void dispose();
}
