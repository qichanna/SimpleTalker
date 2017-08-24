package com.liqi.talker.factory.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.raizlabs.android.dbflow.structure.ModelViewAdapter;

/**
 * Created by liqi7 on 2017/8/24.
 */

public class DBFlowExclusionStrategy implements ExclusionStrategy{
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        // 被跳过的字段
        // 只要是属于DBFlow数据的
        return f.getDeclaredClass().equals(ModelViewAdapter.class);
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        // 被跳过的Class
        return false;
    }
}
