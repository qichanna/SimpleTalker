package com.liqi.talker.factory.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.liqi.talker.factory.Factory;
import com.liqi.talker.factory.model.api.account.AccountRspModel;
import com.liqi.talker.factory.model.db.User;
import com.liqi.talker.factory.model.db.User_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

/**
 * Created by liqi7 on 2017/8/23.
 */

public class Account {
    private static final String KEY_PUSH_ID = "KEY_PUSH_ID";
    private static final String KEY_IS_BIND = "KEY_IS_BIND";
    private static final String KEY_TOKEN = "KEY_TOKEN";
    private static final String KEY_USER_ID = "KEY_USER_ID";
    private static final String KEY_ACCOUNT = "KEY_ACCOUNT";

    // 设备的推送Id
    private static String pushId = "";
    // 设备Id是否已经绑定到了服务器
    private static boolean isBind;
    // 登录状态的Token，用来接口请求
    private static String token;
    // 登录的用户ID
    private static String userId;
    // 登录的账户
    private static String account;

    /**
     * 存储数据到XML文件,持久化
     *
     * @param context
     */
    private static void save(Context context) {
        // 获取数据持久化的SP
        SharedPreferences sp = context.getSharedPreferences(Account.class.getName(),
                Context.MODE_PRIVATE);
        // 存储数据
        sp.edit()
                .putString(KEY_PUSH_ID, pushId)
                .putBoolean(KEY_IS_BIND, isBind)
                .apply();
    }

    /**
     * 进行数据加载
     *
     * @param context
     */
    public static void load(Context context) {
        SharedPreferences sp = context.getSharedPreferences(Account.class.getName(),
                Context.MODE_PRIVATE);
        pushId = sp.getString(KEY_PUSH_ID, "");
        isBind = sp.getBoolean(KEY_IS_BIND, false);
    }

    /**
     * 获取推送Id
     *
     * @return
     */
    public static String getPushId() {
        return pushId;
    }

    /**
     * 设置并存储设备的Id
     *
     * @param pushId
     */
    public static void setPushId(String pushId) {
        Account.pushId = pushId;
        Account.save(Factory.app());
    }

    /**
     * 返回当前账户是否登录
     *
     * @return True已登录
     */
    public static boolean isLogin() {
        // 用户Id 和 Token 不为空
        return !TextUtils.isEmpty(userId)
                 && TextUtils.isEmpty(token);
    }

    /**
     * 是否已经完善了用户信息
     * @return
     */
    public static boolean isComplete(){
        return isLogin();
    }

    /**
     * 是否已经绑定到了服务器
     *
     * @return True已绑定
     */
    public static boolean isBind() {
        return isBind;
    }

    public static void setBind(boolean isBind) {
        Account.isBind = isBind;
        Account.save(Factory.app());
    }

    public static void login(AccountRspModel model) {
        // 存储当前登录的账户, token, 用户Id，方便从数据库中查询我的信息
        Account.token = model.getToken();
        Account.account = model.getAccount();
        Account.userId = model.getUser().getId();
        save(Factory.app());
    }

    public static User getUser(){
        // 如果为null返回一个new的User，其次从数据库查询
        return TextUtils.isEmpty(userId) ? new User() : SQLite.select()
                .from(User.class)
                .where(User_Table.id.eq(userId))
                .querySingle();
    }
}