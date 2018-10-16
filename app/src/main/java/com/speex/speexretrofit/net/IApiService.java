package com.speex.speexretrofit.net;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 所有接口均写在该类下,类名不可修改，如需修改需在其他调用文件相应修改
 */
public interface IApiService {

    @GET("{url}")
    Call<ResponseBody> download(@Path(value = "url", encoded = true) String url);

    //http://aispeech-tvui-public.oss-cn-shenzhen.aliyuncs.com/release/dangbei/version-guide-1.1.json
}
