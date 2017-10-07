package com.liqi.talker.factory.data.helper;

import com.liqi.factory.data.DataSource;
import com.liqi.talker.factory.Factory;
import com.liqi.talker.factory.R;
import com.liqi.talker.factory.model.api.RspModel;
import com.liqi.talker.factory.model.api.user.UserUpdateModel;
import com.liqi.talker.factory.model.card.UserCard;
import com.liqi.talker.factory.model.db.User;
import com.liqi.talker.factory.model.db.User_Table;
import com.liqi.talker.factory.net.Network;
import com.liqi.talker.factory.net.RemoteService;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by liqi on 2017/8/27.
 */

public class UserHelper {
    // 更新用户信息的操作，异步的
    public static void update(UserUpdateModel model, final DataSource.Callback<UserCard> callback){
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Call<RspModel<UserCard>> call = service.userUpdate(model);
        // 网络请求
        call.enqueue(new Callback<RspModel<UserCard>>() {
            @Override
            public void onResponse(Call<RspModel<UserCard>> call, Response<RspModel<UserCard>> response) {
                RspModel<UserCard> rspModel = response.body();
                if(rspModel.success()){
                    UserCard userCard = rspModel.getResult();
                    // 唤起进行保存的操作
                    Factory.getUserCenter().dispatch(userCard);

                    // 数据库的存储操作，需要把UserCard转换为User
                    // 保存用户信息
//                    User user = userCard.build();
                    // 异步统一的保存
//                    DbHelper.save(User.class,user);
//                    user.save();
                    // 返回成功
                    callback.onDataLoaded(userCard);
                }else {
                    // 错误情况下进行错误分配
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<UserCard>> call, Throwable t) {
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }

    // 搜索的方法
    public static Call search(String name, final DataSource.Callback<List<UserCard>> callback){
        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service = Network.remote();
        // 得到一个Call
        Call<RspModel<List<UserCard>>> call = service.userSearch(name);

        call.enqueue(new Callback<RspModel<List<UserCard>>>() {
            @Override
            public void onResponse(Call<RspModel<List<UserCard>>> call, Response<RspModel<List<UserCard>>> response) {
                RspModel<List<UserCard>> rspModel = response.body();
                if(rspModel.success()){
                    // 返回数据
                    callback.onDataLoaded(rspModel.getResult());
                }else {
                    // 错误情况下进行错误分配
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<List<UserCard>>> call, Throwable t) {
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });

        // 把当前的调度者返回
        return call;
    }

    // 关注的网络请求
    public static void follow(String id, final DataSource.Callback<UserCard> callback) {
        RemoteService service = Network.remote();
        final Call<RspModel<UserCard>> call = service.userFollow(id);

        call.enqueue(new Callback<RspModel<UserCard>>() {
            @Override
            public void onResponse(Call<RspModel<UserCard>> call, Response<RspModel<UserCard>> response) {
                RspModel<UserCard> rspModel = response.body();
                if(rspModel.success()){
                    UserCard userCard = rspModel.getResult();
                    // 唤起进行保存的操作
                    Factory.getUserCenter().dispatch(userCard);

                    // 保存到本地数据库
//                    User user = userCard.build();
                    // 保存并通知联系人列表刷新
//                    DbHelper.save(User.class,user);
//                    user.save();
                    // TODO 通知联系人列表刷新
                    // 返回数据
//                    callback.onDataLoaded(rspModel.getResult());
                    callback.onDataLoaded(userCard);
                }else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<UserCard>> call, Throwable t) {
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }

    // 刷新联系人的操作,不需要Callback，直接存储到数据库,并通过数据库观察者进行通知界面更新,界面更新的时候进行对比，然后差异更新
    public static void refreshContacts(){
        RemoteService service = Network.remote();
        service.userContacts()
                .enqueue(new Callback<RspModel<List<UserCard>>>() {
            @Override
            public void onResponse(Call<RspModel<List<UserCard>>> call, Response<RspModel<List<UserCard>>> response) {
                RspModel<List<UserCard>> rspModel = response.body();
                if(rspModel.success()){
                    List<UserCard> cards = rspModel.getResult();
                    if(cards == null || cards.size() == 0)
                        return;

                    UserCard[] cards1 = cards.toArray(new UserCard[0]);
//                    CollectionUtil.toArray(cards,UserCard.class)
                    Factory.getUserCenter().dispatch(cards1);
                    // 返回数据
//                    callback.onDataLoaded(rspModel.getResult());
                }else {
                    Factory.decodeRspCode(rspModel, null);
                }
            }

            @Override
            public void onFailure(Call<RspModel<List<UserCard>>> call, Throwable t) {
                // nothing
            }
        });
    }

    // 从本地查询一个用户的信息
    public static User findFromLocal(String id){
        return SQLite.select()
                .from(User.class)
                .where(User_Table.id.eq(id))
                .querySingle();
    }

    // 从网络查询某用户的信息
    public static User findFromNet(String id){
        RemoteService remoteService = Network.remote();

        try {
            Response<RspModel<UserCard>> response = remoteService.userFind(id).execute();
            UserCard card = response.body().getResult();
            if(card != null){
                // TODO 数据库的存储但是没有通知
                User user = card.build();
                // 数据库的存储并通知
                Factory.getUserCenter().dispatch(card);
//                DbHelper.save(User.class,user);
//                user.save();
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *  搜索一个用户，优先本地缓存,
     *  没有用然后再从网络拉取
     * @param id
     * @return
     */
    public static User search(String id){
        User user = findFromLocal(id);
        if(user == null){
            return findFromNet(id);
        }
        return user;
    }

    /**
     *  搜索一个用户，优先网络查询,
     *  没有用然后再从本地缓存拉取
     * @param id
     * @return
     */
    public static User searchFirstOfNet(String id){
        User user = findFromNet(id);
        if(user == null){
            return findFromLocal(id);
        }
        return user;
    }
}
