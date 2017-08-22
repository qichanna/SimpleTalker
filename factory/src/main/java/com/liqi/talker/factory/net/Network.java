package com.liqi.talker.factory.net;

import com.liqi.common.Common;
import com.liqi.talker.factory.Factory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liqi7 on 2017/8/22.
 */

public class Network {

    // 构建一个Retrofit
    public static Retrofit getRetrofit(){
        // 得到一个OK Client

        OkHttpClient client = new OkHttpClient.Builder().build();

        Retrofit.Builder builder = new Retrofit.Builder();

        //设置电脑链接
        return builder.baseUrl(Common.Constance.API_URL)
                // 设置client
                .client(client)
                // 设置Json解析器
                .addConverterFactory(GsonConverterFactory.create(Factory.getGson()))
                .build();
    }
}
