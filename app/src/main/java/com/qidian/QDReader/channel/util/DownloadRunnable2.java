package com.qidian.QDReader.channel.util;

import android.os.Handler;

/* compiled from: DownloadUtil */
final class DownloadRunnable2 implements Runnable {
    final /* synthetic */ Handler handler;
    final /* synthetic */ CallBack callBack;
    final /* synthetic */ String c;

    DownloadRunnable2(Handler handler, CallBack callBack, String str) {
        this.handler = handler;
        this.callBack = callBack;
        this.c = str;
    }

    public void run() {
        this.handler.post(new DownloadRunnable(this, DownloadUtil.downloadTo("http://download.qidian.com/apknew/source/QDReaderLite.apk", "QDReaderLite.apk", AppPath.downloadPath())));
    }
}
