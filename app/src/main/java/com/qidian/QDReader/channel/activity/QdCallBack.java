package com.qidian.QDReader.channel.activity;

import com.qidian.QDReader.channel.util.CallBack;
import com.qidian.QDReader.channel.util.DownloadUtil;
import com.qidian.QDReader.channel.util.UserActionUtil;

/* compiled from: MainActivity */
class QdCallBack implements CallBack {
    final /* synthetic */ boolean flag;
    final /* synthetic */ MainActivity mainActivity;

    QdCallBack(MainActivity mainActivity, boolean z) {
        this.mainActivity = mainActivity;
        this.flag = z;
    }

    public void a(boolean z, String str) {
        this.mainActivity.flag = false;
        UserActionUtil.set(z ? "qd_Lite_A03" : "qd_Lite_A04");
        if (this.flag) {
            DownloadUtil.installApk(str);
        }
    }
}
