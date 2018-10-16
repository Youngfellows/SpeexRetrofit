package com.speex.speexretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.speex.speexretrofit.entity.FileDownloadEntity;
import com.speex.speexretrofit.http.RetrofitCallback;
import com.speex.speexretrofit.http.RetrofitFileUtils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 下载APK
     *
     * @param view
     */
    public void download(View view) {
        //        String param = "/release/dangbei/tvui-tv/tvui-tv-dangbei-1.0.11.180929.2-1011.apk";
        String param = "/mobilesafe/shouji360/360safesis/360MobileSafe_6.2.3.1060.apk";
        FileDownloadEntity downloadEntity = new FileDownloadEntity(null);
        RetrofitFileUtils.downloadFile(downloadEntity, new RetrofitCallback<ResponseBody>() {
            @Override
            public void onSuccess(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i(TAG, "onSuccess ");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, "onFailure ");
            }

            @Override
            public void onLoading(long total, long progress) {
                super.onLoading(total, progress);
                Log.i(TAG, "onLoading " + (float) (progress * 1.0 / total) * 100 + "%");
            }
        });
    }
}
