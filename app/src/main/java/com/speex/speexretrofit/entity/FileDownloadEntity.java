package com.speex.speexretrofit.entity;

import com.speex.speexretrofit.net.BaseFileDownload;
import com.speex.speexretrofit.net.IApiService;

import retrofit2.Call;

/**
 * 测试下载文件请求实体类(具体更具需求变更)
 * 必须继承BaseFileDownload
 */
public class FileDownloadEntity extends BaseFileDownload {

    private String url;

    public FileDownloadEntity(String url) {
        this.url = url;
    }

    @Override
    public Call getFileDownloadCall(IApiService IApiService) {
        return IApiService.download(url);
    }
}
