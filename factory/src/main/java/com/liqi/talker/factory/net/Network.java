package com.liqi.talker.factory.net;

import android.text.TextUtils;
import android.util.Log;

import com.liqi.common.Common;
import com.liqi.talker.factory.Factory;
import com.liqi.talker.factory.persistence.Account;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liqi7 on 2017/8/22.
 */

public class Network {
    private static Network instance;
    private Retrofit retrofit;

    static {
        instance = new Network();
    }

    private Network(){

    }

    private static Interceptor initLogInterceptor(){
        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        Interceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("NetWorkUrl", "log: " + message);
            }
        });
        ((HttpLoggingInterceptor) interceptor).setLevel(level);
        return interceptor;
    }

    // 构建一个Retrofit
    public static Retrofit getRetrofit(){
        if(instance.retrofit != null){
            return instance.retrofit;
        }
        // 得到一个OK Client

        OkHttpClient client = new OkHttpClient.Builder()
                // 给所有的请求添加一个拦截器
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        // 拿到我们的请求
                        Request original = chain.request();
                        // 重新进行build
                        Request.Builder builder = original.newBuilder();
                        if(!TextUtils.isEmpty(Account.getToken())){
                            // 注入一个token
                            builder.addHeader("token",Account.getToken());
                        }
                        builder.addHeader("Content-Type","application/json");
                        Request newRequest = builder.build();
                        // 返回
                        return chain.proceed(newRequest);
                    }
                })
                //添加日志拦截器
                .addInterceptor(initLogInterceptor())
                .build();

        Retrofit.Builder builder = new Retrofit.Builder();

        //设置电脑链接
        instance.retrofit = builder.baseUrl(Common.Constance.API_URL)
                // 设置client
                .client(client)
                // 设置Json解析器
                .addConverterFactory(GsonConverterFactory.create(Factory.getGson()))
                .build();

        return instance.retrofit;
    }

    /**
     * 返回一个请求代理
     * @return
     */
    public static RemoteService remote(){
        return Network.getRetrofit().create(RemoteService.class);
    }
}
