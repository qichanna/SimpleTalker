package com.liqi.talker.factory.presenter.message;

import android.support.v7.util.DiffUtil;

import com.liqi.talker.factory.data.message.SessionDataSource;
import com.liqi.talker.factory.data.message.SessionRepository;
import com.liqi.talker.factory.model.db.Session;
import com.liqi.talker.factory.presenter.BaseSourcePresenter;
import com.liqi.talker.factory.utils.DiffUiDataCallback;

import java.util.List;

/**
 * 最近聊天列表的Presenter
 *
 * @author qiujuer Email:qiujuer@live.cn
 * @version 1.0.0
 */
public class SessionPresenter extends BaseSourcePresenter<Session, Session,
        SessionDataSource, SessionContract.View> implements SessionContract.Presenter {

    public SessionPresenter(SessionContract.View view) {
        super(new SessionRepository(), view);
    }

    @Override
    public void onDataLoaded(List<Session> sessions) {
        SessionContract.View view = getView();
        if (view == null)
            return;

        // 差异对比
        List<Session> old = view.getRecyclerAdapter().getItems();
        DiffUiDataCallback<Session> callback = new DiffUiDataCallback<>(old, sessions);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(callback);

        // 刷新界面
        refreshData(result, sessions);
    }
}
