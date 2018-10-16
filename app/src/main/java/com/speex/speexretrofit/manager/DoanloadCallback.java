package com.speex.speexretrofit.manager;

import java.io.File;

/**
 * @author henrychen
 * @version $Rev$
 * @email henrychen@aispeech.com
 * @time 2018/10/16 22:57
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 */

public interface DoanloadCallback {

    /**
     * 文件下载成功并保存
     *
     * @param file 保存的文件
     */
    void onSuccess(File file);


    /**
     * 文件下载失败
     *
     * @param error
     */
    void onFailure(Throwable error);

    /**
     * 更新下载进度
     *
     * @param total    文件总大小
     * @param progress 当已经下载进度
     * @param done     是否下载完成
     */
    void onLoading(long total, long progress, boolean done);
}
