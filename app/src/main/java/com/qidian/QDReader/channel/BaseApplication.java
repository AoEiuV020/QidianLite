package com.qidian.QDReader.channel;

import android.app.Application;
import android.content.Context;
import android.webkit.CookieSyncManager;

import com.qidian.QDReader.channel.util.AppInfo;
import com.qidian.QDReader.channel.util.ApplicationContext;
import com.qidian.QDReader.channel.util.UserActionUtil;

public class BaseApplication extends Application {
    public void onCreate() {
        super.onCreate();
        ApplicationContext.setApp(this);
        Qdinit();
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public void Qdinit() {
//        UserAction.initUserAction(this);
//        UserAction.setChannelID("1000147");
        UserActionUtil.set("qd_Lite_A09");
        AppInfo.getInstance().init();
        CookieSyncManager.createInstance(this);
    }
}
