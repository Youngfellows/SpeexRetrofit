package com.speex.speexretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.speex.speexretrofit.manager.DoanloadCallback;
import com.speex.speexretrofit.manager.RetrofitManager;
import com.speex.speexretrofit.utils.URLUtils;

import java.io.File;

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


}
