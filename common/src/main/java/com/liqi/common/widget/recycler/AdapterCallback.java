package com.liqi.common.widget.recycler;

/**
 * Created by liqi7 on 2017/6/12.
 */

public interface AdapterCallback<Data> {
    void update(Data data, RecyclerAdapter.ViewHolder<Data> holder);
}
