package com.liqi.talker.factory.data.user;

import android.support.annotation.NonNull;

import com.liqi.factory.data.DataSource;
import com.liqi.talker.factory.data.helper.DbHelper;
import com.liqi.talker.factory.model.db.User;
import com.liqi.talker.factory.model.db.User_Table;
import com.liqi.talker.factory.persistence.Account;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** 联系人仓库
 * Created by liqi on 2017/10/8.
 */

public class ContactRepository implements ContactDataSource,
        QueryTransaction.QueryResultListCallback<User>,
        DbHelper.ChangedListener<User>{
    private final Set<User> users = new HashSet<>();
    private DataSource.SucceedCallback<List<User>> callback;

    @Override
    public void load(DataSource.SucceedCallback<List<User>> callback) {
        this.callback = callback;
        // 对数据辅助工具类添加一个数据更新的监听
        DbHelper.addChangedListener(User.class,this);

        // 加载本地数据库数据
        SQLite.select()
                .from(User.class)
                .where(User_Table.isFollow.eq(true))
                .and(User_Table.id.notEq(Account.getUserId()))
                .orderBy(User_Table.name, true)
                .limit(100)
                .async()
                .queryListResultCallback(this)
                .execute();
    }

    @Override
    public void dispose() {
        this.callback = null;
        // 取消对数据集合的监听
        DbHelper.removeChangedListener(User.class,this);
    }

    @Override
    public void onListQueryResult(QueryTransaction transaction, @NonNull List<User> tResult) {
        // 添加到自己当前的缓冲区
        users.addAll(tResult);
        // 数据库加载数据成功
        if(callback != null){
            callback.onDataLoaded(tResult);
        }
    }

    @Override
    public void onDateSave(User... list) {
        // 当数据库数据变更的操作

        for (User user : list) {
            // 是关注的人，同时不是我自己
            if(isRequired(user)){

            }
        }
    }

    @Override
    public void onDateDelete(User... list) {
        // 当数据库数据删除的操作
    }

    private void insertOrUpdate(User user){
        boolean index = users.contains(user);
    }

    /**
     *  检查一个User是否是我需要关注的数据
     * @param user
     * @return True 是我关注的数据
     */
    private boolean isRequired(User user){
        return user.isFollow() && !user.getId().equals(Account.getUserId());
    }
}
