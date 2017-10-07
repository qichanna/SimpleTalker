package com.liqi.talker.factory.data.user;

import com.liqi.talker.factory.model.card.UserCard;

/**
 *  用户中心的基本定义
 * Created by liqi on 2017/10/6.
 */

public interface UserCenter {
    // 分发处理一堆用户卡片的信息，并更新到数据库
    void dispatch(UserCard... cards);
}
