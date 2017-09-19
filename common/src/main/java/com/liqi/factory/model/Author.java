package com.liqi.factory.model;

/** 基础用户接口
 * Created by liqi on 2017/9/19.
 */

public interface Author {
    String getId();

    void setId(String id);

    String getName();

    void setName(String name);

    String getPortrait();

    void setPortrait(String portrait);
}
