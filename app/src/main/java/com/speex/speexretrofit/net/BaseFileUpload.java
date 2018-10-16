package com.speex.speexretrofit.net;

import retrofit2.Call;

/**
 * 文件上传基类
 */
public abstract class BaseFileUpload {

    public abstract Call getFileUploadCall(IApiService IApiService);
}
