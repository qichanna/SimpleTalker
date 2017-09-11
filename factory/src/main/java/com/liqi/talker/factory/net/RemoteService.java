package com.liqi.talker.factory.net;

/**
 * Created by liqi7 on 2017/8/22.
 */

import com.liqi.talker.factory.model.UserCard.UserCard;
import com.liqi.talker.factory.model.api.RspModel;
import com.liqi.talker.factory.model.api.account.AccountRspModel;
import com.liqi.talker.factory.model.api.account.LoginModel;
import com.liqi.talker.factory.model.api.account.RegisterModel;
import com.liqi.talker.factory.model.api.user.UserUpdateModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * 网络请求的所有的接口
 */
public interface RemoteService {

    /**
     * 注册接口
     * @param model  传入的是RegisterModel
     * @return 返回的是RspModel<AccountRspModel>
     */
    @POST("account/register")
    Call<RspModel<AccountRspModel>> accountRegister(@Body RegisterModel model);

    /**
     * 登录接口
     * @param model
     * @return
     */
    @POST("account/login")
    Call<RspModel<AccountRspModel>> accountLogin(@Body LoginModel model);


    /**
     * 绑定设备Id
     * @param pushId
     * @return
     */
    @POST("account/bind/{pushId}")
    Call<RspModel<AccountRspModel>> accountBind(@Path(encoded = true, value = "pushId") String pushId);

    // 用户更新的接口
    @PUT("user")
    Call<RspModel<UserCard>> userUpdate(@Body UserUpdateModel model);

    // 用户搜索的接口
    @GET("user/search/{name}")
    Call<RspModel<List<UserCard>>> userSearch(@Path("name") String name);
}
