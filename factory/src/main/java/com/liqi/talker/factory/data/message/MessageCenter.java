package com.liqi.talker.factory.data.message;

import com.liqi.talker.factory.model.card.MessageCard;

/**
 * Created by liqi on 2017/10/6.
 */

/**
 *  消息中心, 进行消息卡片的消费
 */
public interface MessageCenter {
    void dispatch(MessageCard... cards);
}
