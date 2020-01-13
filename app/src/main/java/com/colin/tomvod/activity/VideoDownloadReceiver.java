package com.colin.tomvod.activity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.colin.tomvod.bean.VideoDownLoad;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.litepal.LitePal;

public class VideoDownloadReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {
            FancyToast.makeText(context, "下载成功，请到缓存查看", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
//            DownloadManager.Query query = new DownloadManager.Query();
//            //在广播中取出下载任务的id
            // TODO: 2020/1/10 有问题，查询不到。后期修改
//            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
//            VideoDownLoad videoDownLoad = LitePal.find(VideoDownLoad.class, id);
//            videoDownLoad.setFinished(true);
//            Uri uri =manager.getUriForDownloadedFile(id);
//            videoDownLoad.save();
//            query.setFilterById(id);
//            Cursor c = manager.query(query);
//            if (c.moveToFirst()) {
//                //获取文件下载路径
//                final ContentResolver resolver = context.getContentResolver();
//                Uri uriForDownloadedFile = manager.getUriForDownloadedFile(id);
//
//            }
        } else if (DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(intent.getAction())) {
            long[] ids = intent.getLongArrayExtra(DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS);
            //点击通知栏取消下载
            manager.remove(ids);
            Toast.makeText(context, "已经取消下载", Toast.LENGTH_LONG).show();
        }
    }
}
