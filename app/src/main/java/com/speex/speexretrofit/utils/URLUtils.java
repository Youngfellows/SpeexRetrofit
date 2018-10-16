package com.speex.speexretrofit.utils;

import android.text.TextUtils;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author henrychen
 * @version $Rev$
 * @email henrychen@aispeech.com
 * @time 2018/10/16 21:57
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public class URLUtils {
    private static String TAG = "URLUtils";

    /**
     * 或者指定url的域名
     *
     * @param path
     * @return
     */
    public static String getHost(String path) {
        String host = null;
        try {
            URL url = new URL(path);
            host = "http://" + url.getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "host = " + host);
        return host;
    }


    /**
     * 获取指定域名后的参数串
     *
     * @param path
     * @return
     */
    public static String getUrl(String path) {
        String key = null;
        try {
            URL url = new URL(path);
            String host = url.getHost();
            if (!TextUtils.isEmpty(host)) {
                String[] splits = path.split(host);
                for (int i = 0; i < splits.length; i++) {

                }
                if (splits != null && splits.length == 2) {
                    key = splits[1];
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "key = " + key);
        return key;
    }
}
