package com.speex.speexretrofit.http;

import retrofit2.Call;

/**
 * 文件下载请求基类
 */
public abstract class BaseFileDownload {
    public abstract Call getFileDownloadCall(RetrofitService retrofitService);
}
