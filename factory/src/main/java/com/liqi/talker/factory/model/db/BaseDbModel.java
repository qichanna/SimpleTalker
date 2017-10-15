package com.liqi.talker.factory.model.db;

import com.liqi.talker.factory.utils.DiffUiDataCallback;
import com.raizlabs.android.dbflow.structure.BaseModel;

/** 我们App中的基础的一个BaseDbModel,
 *  基础了数据库框架DbFlow中的基础类
 *  同时定义类我们需要的方法
 * Created by liqi on 2017/10/15.
 */

public abstract class BaseDbModel<Model> extends BaseModel
        implements DiffUiDataCallback.UiDataDiffer<Model>{
}
