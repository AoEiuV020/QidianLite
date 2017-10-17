package com.qidian.QDReader.channel.util;

/* compiled from: DownloadUtil */
class DownloadRunnable implements Runnable {
    final /* synthetic */ boolean flag;
    final /* synthetic */ DownloadRunnable2 runnable;

    DownloadRunnable(DownloadRunnable2 downloadRunnable2, boolean flag) {
        this.runnable = downloadRunnable2;
        this.flag = flag;
    }

    public void run() {
        this.runnable.callBack.a(this.flag, this.runnable.c);
    }
}
