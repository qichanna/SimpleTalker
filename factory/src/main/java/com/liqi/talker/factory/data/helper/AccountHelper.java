package com.liqi.talker.factory.data.helper;

import com.liqi.factory.data.DataSource;
import com.liqi.talker.factory.Factory;
import com.liqi.talker.factory.R;
import com.liqi.talker.factory.model.api.RspModel;
import com.liqi.talker.factory.model.api.account.AccountRspModel;
import com.liqi.talker.factory.model.api.account.RegisterModel;
import com.liqi.talker.factory.model.db.User;
import com.liqi.talker.factory.net.Network;
import com.liqi.talker.factory.net.RemoteService;
import com.liqi.talker.factory.persistence.Account;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by liqi on 2017/8/21.
 */

public class AccountHelper {

    /**
     * 注册接口，异步的调用
     * @param model 传递一个注册的Modle进来
     * @param callback 成功与失败的接口回送
     */
    public static void register(RegisterModel model, final DataSource.Callback<User> callback){
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                callback.onDataNotAvailable(R.string.data_rsp_error_parameters);
//            }
//        }.start();

        // 调用Retrofit对我们的网络请求接口做代理
        RemoteService service =Network.getRetrofit().create(RemoteService.class);
        // 得到一个Call
        Call<RspModel<AccountRspModel>> call = service.accountRegister(model);

        call.enqueue(new Callback<RspModel<AccountRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<AccountRspModel>> call, Response<RspModel<AccountRspModel>> response) {
                // 请求成功返回
                // 从返回中得到我们的全局Model, 内部是使用Gson解析
                RspModel<AccountRspModel> rspModel = response.body();
                if (rspModel.success()){
                    //拿到实体
                    AccountRspModel accountRspModel = rspModel.getResult();
                    // 判断绑定状态,是否绑定设备
                    if(accountRspModel.isBind()){
                        User user = accountRspModel.getUsr();
                        // 写入数据库
                        callback.onDataLoaded(user);
                    }else {
                        // 进行绑定的换气
//                        bindPush(callback);
                        User user = accountRspModel.getUsr();
                        callback.onDataLoaded(user);
                    }
                }else {
                    Factory.decodeRspCode(rspModel,callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<AccountRspModel>> call, Throwable t) {
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }

    public static void bindPush(final DataSource.Callback<User> callback){
        callback.onDataNotAvailable(R.string.app_name);
    }
}
