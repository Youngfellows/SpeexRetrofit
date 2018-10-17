package com.speex.speexretrofit.net;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 所有接口均写在该类下,类名不可修改，如需修改需在其他调用文件相应修改
 */
public interface IApiService {

    /**
     * 文件下载
     *
     * @param url
     * @return
     */
    @GET("{url}")
    Call<ResponseBody> download(@Path(value = "url", encoded = true) String url);

    //http://aispeech-tvui-public.oss-cn-shenzhen.aliyuncs.com/release/dangbei/version-guide-1.1.json

    /**
     * 请求更新配置
     *
     * @param url
     * @return
     */
    @GET("{url}")
    Call<ResponseBody> upgradeConfig(@Path(value = "url", encoded = true) String url);

    /**
     * 触发网络请求,先拉取是否可以更新
     * mBaseUrl: http://test.iot.aispeech.com:8089/skyline-iot-api/api/v2/tv/versionUpgrade ,productId: 278572232 ,deviceId: 4d07e4be9184e15a8b483c97077e171b
     * url = http://test.iot.aispeech.com:8089/skyline-iot-api/api/v2/tv/versionUpgrade?productId=278572232&versionCode=1005&deviceId=4d07e4be9184e15a8b483c97077e171b&packageName=com.aispeech.tvui
     */

    /**
     * 请求版本更新
     *
     * @param url 请求URL
     * @param map
     * @return
     */
    @GET("{url}")
    Call<ResponseBody> upgradeVersion(@Path(value = "url", encoded = true) String url, @QueryMap Map<String, String> map);


    /**
     * 请求版本更新
     *
     * @param url         请求URL
     * @param productId   产品ID
     * @param versionCode 版本号
     * @param deviceId    设备号
     * @param packageName 包名
     * @return
     */
    @GET("{url}")
    Call<ResponseBody> upgradeVersion(@Path(value = "url", encoded = true) String url, @Query("productId") String productId, @Query("versionCode") String versionCode, @Query("deviceId") String deviceId, @Query("packageName") String packageName);

}
