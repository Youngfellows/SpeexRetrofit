package com.speex.speexretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.speex.speexretrofit.bean.Content;
import com.speex.speexretrofit.bean.UpdateAppConfigBean;
import com.speex.speexretrofit.interfaces.UpgradeRequestCallBack;
import com.speex.speexretrofit.interfaces.DoanloadCallback;
import com.speex.speexretrofit.manager.RetrofitManager;
import com.speex.speexretrofit.utils.URLUtils;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String TAG = this.getClass().getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 获取域名
     *
     * @param view
     */
    public void getHost(View view) {
        String path = "http://aispeech-tvui-public.oss-cn-shenzhen.aliyuncs.com/release/dangbei/tvui-tv/tvui-tv-dangbei-1.0.11.180929.2-1011.apk";
        Log.i(TAG, URLUtils.getHost(path));
        Log.i(TAG, URLUtils.getUrl(path));

    }


    /**
     * 下载APK
     *
     * @param view
     */
    public void download(View view) {
        //        final String fileUrl = "http://aispeech-tvui-public.oss-cn-shenzhen.aliyuncs.com/release/dangbei/tvui-tv/tvui-tv-dangbei-1.0.11.180929.2-1011.apk";
        //        final String fileUrl = "http://bsl-cdn.ottboxer.cn/apkmarket_file/app/video/iqiyi_voice/GitvVideo-release-one-weihaokeji-kaV11906-tv8.1.0-r73356-gitv-auto_player.apk";

        //        final String fileUrl = "http://bsl-cdn.ottboxer.cn/apkmarket_file/app/system_recommend/gqzbncb/BA_LIVE[BETA]_ALI_20180831_10022.apk";

        final String fileUrl = "http://ksyun-cdn.ottboxer.cn/apkmarket_file/app/video/CIBN_dangbei/10034989_CIBN_CoolMiao_dangbei12.apk";

        //        final String fileUrl = "http://ksyun-cdn.ottboxer.cn/apkmarket_file/app/video/ystjg/tv_video_3.3.2.2020_android_13090.apk";

        //        final String fileUrl = "http://ksyun-cdn.ottboxer.cn/apkmarket_file/app/online_music/QQmusic_TV/qqyy_3.2.0.7_dangbei.apk";

        RetrofitManager.getInstance().download(fileUrl, "sdcard/Download/", "download222.apk", new DoanloadCallback() {
            @Override
            public void onSuccess(File file) {
                Log.i(TAG, "onSuccess path: " + file.getPath());
            }

            @Override
            public void onFailure(Throwable error) {
                Log.e(TAG, "onFailure " + error.getMessage());
            }

            @Override
            public void onLoading(long total, long progress, boolean done) {
                Log.i(TAG, "22 onLoading " + (float) (progress * 1.0 / total) * 100 + "% , " + (done ? "下载完成" : "未下载完成"));
            }
        });
    }


    /**
     * 更新配置单
     *
     * @param view
     */
    public void upgradeConfig(View view) {
        //        String configUrl = "http://aispeech-tvui-public.oss-cn-shenzhen.aliyuncs.com/release/dangbei/version-guide-1.1.json";
        String configUrl = "http://aispeech-tvui-public.oss-cn-shenzhen.aliyuncs.com/release/tvuipublic/version-guide-1.1.json";

        RetrofitManager.getInstance().upgradeConfig(configUrl, new UpgradeRequestCallBack() {
            @Override
            public void requestSuccess(String data) {

                UpdateAppConfigBean configBean = JSON.parseObject(data, UpdateAppConfigBean.class);
                List<Content> appArray = configBean.getContent();
                Content tvuiApp = null;//TVUI下载信息
                for (Content app : appArray) {
                    String component = app.getComponent();
                    String mDownVersionName = app.getVersionName();
                    int mDownVersionCode = Integer.parseInt(app.getVersionCode());
                    String mMd5 = app.getMd5();//APK的MD5值
                    String changeLog = app.getChangeLog();
                    String changlog = changeLog;
                    String appUrl = app.getUrl();

                    Log.d(TAG, "component: " + component + "\nversionCode: " + mDownVersionCode + "\nversionName: " + mDownVersionName + "\nmd5: " + mMd5 + "\nappUrl: " + appUrl + "\nchangeLog: " + changeLog);

                }
            }

            @Override
            public void requestError(String exception) {
                Log.e(TAG, "requestError " + exception);
            }
        });
    }

    /**
     * 获取版本更新信息
     *
     * @param view
     */
    public void upgradeVersion(View view) {

        /**
         * 触发网络请求,先拉取是否可以更新
         * mBaseUrl: http://test.iot.aispeech.com:8089/skyline-iot-api/api/v2/tv/versionUpgrade ,productId: 278572232 ,deviceId: 4d07e4be9184e15a8b483c97077e171b
         * url = http://test.iot.aispeech.com:8089/skyline-iot-api/api/v2/tv/versionUpgrade?productId=278572232&versionCode=1005&deviceId=4d07e4be9184e15a8b483c97077e171b&packageName=com.aispeech.tvui
         */
        //        String url = "http://test.iot.aispeech.com:8089/skyline-iot-api/api/v2/tv/versionUpgrade";//测试环境
        String url = "http://api.iot.aispeech.com/skyline-iot-api/api/v2/tv/versionUpgrade";//正式环境
        String productId = "278572232";
        String versionCode = "1005";
        String deviceId = "4d07e4be9184e15a8b483c97077e171b";
        String packageName = "com.aispeech.tvui";

        //        mBaseUrl: http://test.iot.aispeech.com:8089/skyline-iot-api/api/v2/tv/versionUpgrade ,productId: 278572232 ,deviceId: 4d07e4be9184e15a8b483c97077e171b
        //        url = http://test.iot.aispeech.com:8089/skyline-iot-api/api/v2/tv/versionUpgrade?productId=278572232&versionCode=1005&deviceId=4d07e4be9184e15a8b483c97077e171b&packageName=com.aispeech.tvui

        RetrofitManager.getInstance().upgradeVersion(url, productId, versionCode, deviceId, packageName, new UpgradeRequestCallBack() {
            @Override
            public void requestSuccess(String data) {
                Log.i(TAG, "requestSuccess 版本更新信息==>> " + data);
            }

            @Override
            public void requestError(String exception) {
                Log.e(TAG, "requestError " + exception);
            }
        });
    }

}
