package com.liqi.talker.factory.presenter.contact;

import android.support.v7.util.DiffUtil;

import com.liqi.common.widget.recycler.RecyclerAdapter;
import com.liqi.factory.data.DataSource;
import com.liqi.talker.factory.data.helper.UserHelper;
import com.liqi.talker.factory.data.user.ContactDataSource;
import com.liqi.talker.factory.data.user.ContactRepository;
import com.liqi.talker.factory.model.db.User;
import com.liqi.talker.factory.presenter.BaseSourcePresenter;
import com.liqi.talker.factory.utils.DiffUiDataCallback;

import java.util.List;

/** 联系人的Presenter实现
 * Created by liqi on 2017/9/20.
 */

public class ContactPresenter extends BaseSourcePresenter<User,User, ContactDataSource,ContactContract.View>
        implements ContactContract.Presenter,DataSource.SucceedCallback<List<User>>{

    public ContactPresenter(ContactContract.View view) {
        // 初始化数据仓库
        super(new ContactRepository(), view);
    }

    @Override
    public void start() {
        super.start();

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
}
