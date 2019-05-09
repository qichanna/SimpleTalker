package com.liqi.talker.factory.data.message;

import com.liqi.factory.data.DbDataSource;
import com.liqi.talker.factory.model.db.Message;

/**
 * 消息的数据源定义，他的实现是：MessageRepository, MessageGroupRepository
 * 关注的对象是Message表
 *
 * @author qiujuer Email:qiujuer@live.cn
 * @version 1.0.0
 */
public interface MessageDataSource extends DbDataSource<Message> {
}
