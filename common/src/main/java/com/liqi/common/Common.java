package com.liqi.common;

/**
 * Created by liqi on 2017/6/11.
 */

public class Common {

    public interface Constance{
        // 手机号的正则,11位手机号
        String REGEX_MOBILE = "[1][3,4,5,7,8][0-9]{9}$";

        // 基础的网络请求地址
        String API_URL = "http://192.168.137.1:8080/api/";
    }
}
