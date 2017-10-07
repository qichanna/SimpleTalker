package com.liqi.talker.factory.data.group;

import com.liqi.talker.factory.model.card.GroupCard;
import com.liqi.talker.factory.model.card.GroupMemberCard;

/** 群中心的接口定义
 * Created by liqi on 2017/10/6.
 */

public interface GroupCenter {
    // 群卡片的处理
    void dispatch(GroupCard... cards);

    // 群成员的处理
    void dispatch(GroupMemberCard... cards);
}
