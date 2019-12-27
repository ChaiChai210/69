package com.colin.tomvod.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.liulishuo.okdownload.DownloadTask;

public class CancelReceiver extends BroadcastReceiver {
    public static final String ACTION = "cancelOkdownload";

    private DownloadTask task;

   public void setTask(DownloadTask task)
   {
       this.task = task;
   }
    @Override
    public void onReceive(Context context, Intent intent) {
        this.task.cancel();
    }
}
