package com.speex.speexretrofit.manager;

import android.util.Log;

import com.speex.speexretrofit.entity.FileDownloadEntity;
import com.speex.speexretrofit.net.RetrofitCallback;
import com.speex.speexretrofit.net.RetrofitFileUtils;
import com.speex.speexretrofit.utils.URLUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author henrychen
 * @version $Rev$
 * @email henrychen@aispeech.com
 * @time 2018/10/16 21:29
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public class RetrofitFileManager {
    private String TAG = "RetrofitFileManager";
    private static RetrofitFileManager instance;

    private RetrofitFileManager() {

    }

    public static RetrofitFileManager getInstance() {
        if (instance == null) {
            synchronized (RetrofitFileManager.class) {
                if (instance == null) {
                    instance = new RetrofitFileManager();
                }
            }
        }
        return instance;
    }

    /**
     * 下载文件
     *
     * @param fileUrl  下载文件url
     * @param dirPath  保存的目录名称
     * @param fileName 保存的文件名
     * @param callback 下载回调
     */
    public void download(String fileUrl, final String dirPath, final String fileName, final DoanloadCallback callback) {

        //        String path = "http://aispeech-tvui-public.oss-cn-shenzhen.aliyuncs.com/release/dangbei/tvui-tv/tvui-tv-dangbei-1.0.11.180929.2-1011.apk";
        //        fileUrl = "http://ksyun-cdn.ottboxer.cn/apkmarket_file/app/video/ystjg/tv_video_3.3.2.2020_android_13090.apk";

        String url = URLUtils.getUrl(fileUrl);
        String baseUrl = URLUtils.getHost(fileUrl);
        Log.i(TAG, baseUrl);
        Log.i(TAG, url);

        FileDownloadEntity downloadEntity = new FileDownloadEntity(url);
        RetrofitFileUtils.downloadFile(baseUrl, downloadEntity, new RetrofitCallback<ResponseBody>() {
            @Override
            public void onSuccess(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i(TAG, "onSuccess ");
                try {
                    InputStream is = response.body().byteStream();
                    Log.i("TAG", "is = " + is.toString());

                    // String path = "/sdcard/Download";
                    // File f = new File(path);

                    File f = new File(dirPath);
                    if (!f.exists()) {
                        f.mkdirs();
                    }

                    //File file = new File(f, "download.apk");
                    File file = new File(f, fileName);
                    if (file.exists()) {
                        Log.i(TAG, "文件已经纯在了，删除");
                        file.delete();
                    }

                    FileOutputStream fos = new FileOutputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = bis.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                    }
                    fos.flush();
                    fos.close();
                    bis.close();
                    is.close();
                    Log.i(TAG, "文件保存成功");

                    if (callback != null) {
                        callback.onSuccess(file);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i(TAG, "onFailure " + t.getMessage());
                if (callback != null) {
                    callback.onFailure(t);
                }
            }

            @Override
            public void onLoading(long total, long progress, boolean done) {
                //super.onLoading(total, progress, done);
                Log.i(TAG, "onLoading " + (float) (progress * 1.0 / total) * 100 + "% , " + (done ? "下载完成" : "未下载完成"));
                if (callback != null) {
                    callback.onLoading(total, progress, done);
                }
            }
        });
    }
}
