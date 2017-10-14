package com.liqi.talker.factory.presenter.contact;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.util.DiffUtil;

import com.liqi.common.widget.recycler.RecyclerAdapter;
import com.liqi.factory.data.DataSource;
import com.liqi.factory.presenter.BasePresenter;
import com.liqi.factory.presenter.BaseRecyclerPresenter;
import com.liqi.talker.factory.data.helper.UserHelper;
import com.liqi.talker.factory.data.user.ContactDataSource;
import com.liqi.talker.factory.data.user.ContactRepository;
import com.liqi.talker.factory.model.card.UserCard;
import com.liqi.talker.factory.model.db.AppDatabase;
import com.liqi.talker.factory.model.db.User;
import com.liqi.talker.factory.model.db.User_Table;
import com.liqi.talker.factory.persistence.Account;
import com.liqi.talker.factory.utils.DiffUiDataCallback;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;

import java.util.ArrayList;
import java.util.List;

/** 联系人的Presenter实现
 * Created by liqi on 2017/9/20.
 */

public class ContactPresenter extends BaseRecyclerPresenter<User,ContactContract.View>
        implements ContactContract.Presenter,DataSource.SucceedCallback<List<User>>{

    private ContactDataSource mSource;

    public ContactPresenter(ContactContract.View view) {
        super(view);
        mSource = new ContactRepository();
    }

    @Override
    public void start() {
        super.start();

        // 进行本地的数据加载，并添加更新
        mSource.load(this);

        // 加载网络数据
        UserHelper.refreshContacts();

        // TODO 问题:
        // 1.关注后虽然存储数据库，但是没有刷新联系人
        // 2.如果刷新数据库，或者从网络刷新，最终刷新的时候是全部刷新
        // 3.本地刷新和网络刷新，在添加到界面的时候会有可能冲突,导致数据显示异常
        // 3.如果识别已经在数据库中有这样的数据了
    }

    // 运行到这里的时候是子线程
    @Override
    public void onDataLoaded(List<User> users) {
        // 无论怎么操作, 数据变更, 最终都会通知到这里来

        final ContactContract.View view = getView();
        if(view == null)
            return;

        RecyclerAdapter<User> adapter = view.getRecyclerAdapter();
        List<User> old = adapter.getItems();

        // 进行数据对比
        DiffUtil.Callback callback = new DiffUiDataCallback<>(old,users);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);

        // 调用基类方法进行界面刷新
        refreshData(result,users);

    }

    @Override
    public void destroy() {
        super.destroy();
        // 当界面销毁的时候,我们应该把数据监听进行销毁
        mSource.dispose();
    }
}
