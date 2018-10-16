package com.speex.speexretrofit.http;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * 所有接口均写在该类下,类名不可修改，如需修改需在其他调用文件相应修改
 */
public interface RetrofitService {

    //测试接口
//    public static final String BASE_URL = "http://msoftdl.360.cn/";
//    public static final String BASE_URL = "http://192.168.1.232:9190/api/";
    public static final String BASE_URL = "http://aispeech-tvui-public.oss-cn-shenzhen.aliyuncs.com/";

//    @GET("/mobilesafe/shouji360/360safesis/360MobileSafe_6.2.3.1060.apk")


    @GET("/release/dangbei/tvui-tv/tvui-tv-dangbei-1.0.11.180929.2-1011.apk")
    Call<ResponseBody> download();

}
