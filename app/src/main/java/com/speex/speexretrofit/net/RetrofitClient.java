package com.speex.speexretrofit.net;

import android.text.TextUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by huijieZ on 2018/8/27.
 *
 * @author huijieZ
 */

public class RetrofitClient {
    private static final int DEFAULT_TIMEOUT = 5;
    /**
     * 接口类
     */
    private IApiService iApiService;
    private OkHttpClient okHttpClient;
    private static String baseUrl = "https://api.github.com";

    private static Retrofit.Builder builder;

    /**
     * 带参构造方法
     *
     * @param url 要访问地址的baseUrl
     */
    private RetrofitClient(String url) {
        if (TextUtils.isEmpty(url)) {
            url = baseUrl;
        }
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(
                        new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build();

        iApiService = retrofit.create(IApiService.class);
    }

    /**
     * 带参实例化方法
     *
     * @param url baseUrl
     * @return 返回RetrofitClient对象(Retrofit已经初始化完毕)
     */
    public static RetrofitClient getInstance(String url) {
        return new RetrofitClient(url);
    }

    public IApiService getiApiService() {
        return iApiService;
    }
}
