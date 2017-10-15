package com.liqi.factory.data;

import java.util.List;

/**
 * Created by liqi on 2017/10/15.
 */

/**
 *  基础的数据库数据源接口定义
 * @param <Data>
 */
public interface DbDataSource<Data> extends DataSource {
    /**
     *  有一个基本的数据源加载方法
     * @param callback 传递一个callback回调, 一般回调到Presenter
     */
    void load(SucceedCallback<List<Data>> callback);
}
