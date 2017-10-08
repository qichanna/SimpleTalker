package com.liqi.talker.factory.data.helper;

import com.liqi.talker.factory.model.db.Session;
import com.liqi.talker.factory.model.db.Session_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

/**
 * 会话辅助工具类
 * Created by liqi on 2017/10/8.
 */

public class SessionHelper {
    public static Session findFromLocal(String id) {
        // 从本地查询Session
        return SQLite.select()
                .from(Session.class)
                .where(Session_Table.id.eq(id))
                .querySingle();
    }
}
