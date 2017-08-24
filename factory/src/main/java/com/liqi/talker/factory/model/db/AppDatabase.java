package com.liqi.talker.factory.model.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * 数据库的基本信息
 * Created by liqi7 on 2017/8/24.
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {
    public static final String NAME = "AppDatabase";
    public static final int VERSION = 1;
}
