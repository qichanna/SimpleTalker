package com.liqi.simpletalker.frags.message;

import com.liqi.simpletalker.R;
import com.liqi.talker.factory.model.db.Group;
import com.liqi.talker.factory.presenter.message.ChatContract;

/**
 * 群聊天界面实现
 */
public class ChatGroupFragment extends ChatFragment<Group>
        implements ChatContract.GroupView {


    public ChatGroupFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_chat_group;
    }

    @Override
    protected ChatContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void onInit(Group group) {

    }
}
