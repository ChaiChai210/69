package com.colin.tomvod.utils;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.util.Objects;

public class DownLoadUtils {
    private static final String TAG = "DownLoadUtils";

    /**
     * downloadManager 是否可用
     *
     * @param context 上下文
     * @return true 可用
     */
    private static boolean downLoadMangerIsEnable(Context context) {
        int state = context.getApplicationContext().getPackageManager()
                .getApplicationEnabledSetting("com.android.providers.downloads");
        return !(state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED ||
                state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER
                || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED);
    }

    /**
     * 从浏览器打开下载，暂时没有选择应用市场，因为应用市场太多，而且协议不同，无法兼顾所有
     */
    private static void downloadFromBrowse(Context context, String downUrl) {
        try {
            Intent intent = new Intent();
            Uri uri = Uri.parse(downUrl);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(uri);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "无法通过浏览器下载！");
        }
    }

    public static long startDownload(Context context, String downUrl, String fileName) {
//        if (!downLoadMangerIsEnable(context)) {
//            downloadFromBrowse(context, downUrl);
//            return -2;
//        }

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downUrl));
        request.setAllowedOverRoaming(false);//漫游网络是否可以下载

        //设置文件类型，可以在下载结束后自动打开该文件
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(downUrl));
//        request.setMimeType(mimeString);

        //在通知栏中显示，默认就是显示的
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE | DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setVisibleInDownloadsUi(true);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        //sdcard的目录下的download文件夹，必须设置
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_MOVIES + "/vod", fileName + ".mp4");
        //request.setDestinationInExternalFilesDir(),也可以自己制定下载路径
        request.setTitle(fileName);
        // 部分机型（暂时发现Nexus 6P）无法下载，猜测原因为默认下载通过计量网络连接造成的，通过动态判断一下
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            boolean activeNetworkMetered = connectivityManager.isActiveNetworkMetered();
            request.setAllowedOverMetered(activeNetworkMetered);
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            request.allowScanningByMediaScanner();
        }
        //将下载请求加入下载队列
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        //加入下载队列后会给该任务返回一个long型的id，
        //通过该id可以取消任务，重启任务等等，看上面源码中框起来的方法
        return Objects.requireNonNull(downloadManager).enqueue(request);
    }

}
