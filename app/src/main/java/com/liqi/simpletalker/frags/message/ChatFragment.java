package com.liqi.simpletalker.frags.message;

import android.os.Bundle;

import com.liqi.common.app.Fragment;
import com.liqi.simpletalker.activities.MessageActivity;

public abstract class ChatFragment extends Fragment {

    private String mReceiverId;

    @Override
    protected void initArgs(Bundle bundle) {
        super.initArgs(bundle);
        mReceiverId = bundle.getString(MessageActivity.KEY_RECEIVER_ID);
    }
}
