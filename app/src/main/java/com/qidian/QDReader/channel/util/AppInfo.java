package com.qidian.QDReader.channel.util;

import android.content.pm.PackageInfo;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/* compiled from: AppInfo */
public class AppInfo {
    public static AppInfo instance;
    public String deviceId;
    public String id;
    public String version;
    public String bookId;
    public String userAgent;
    public String whatNumber = "1000147";

    public static AppInfo getInstance() {
        if (instance == null) {
            instance = new AppInfo();
        }
        return instance;
    }

    public AppInfo() {
    }

    public void init() {
        this.deviceId = makeDeviceId();
        this.id = makeId();
        this.version = "1";
        try {
            byte[] a = FileUtil.readFile(ApplicationContext.getApp(), "config.txt");
            if (a != null) {
                String[] split = new String(a).split("\\|");
                if (split.length > 0) {
                    this.bookId = split[0];
                }
            }
            PackageInfo packageInfo = ApplicationContext.getApp().getPackageManager().getPackageInfo(ApplicationContext.getApp().getPackageName(), 0);
            this.userAgent = " Mozilla/mobile/QDReaderAndroidLite/" + packageInfo.versionName + "/" + packageInfo.versionCode + "/" + this.whatNumber + "/" + makeDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String makeDeviceId() {
        String str = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) ApplicationContext.getApp().getSystemService("phone");
            if (telephonyManager.getDeviceId() != null) {
                return telephonyManager.getDeviceId();
            }
            return makeAndroidId();
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public String makeAndroidId() {
        try {
            return Secure.getString(ApplicationContext.getApp().getContentResolver(), "android_id");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String makeId() {
        String qimei = ""; //UserAction.getQIMEI();
        return TextUtils.isEmpty(qimei) ? makeDeviceId() : qimei;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getId() {
        return this.id;
    }

    public String getVersion() {
        return this.version;
    }

    public String getBookId() {
        return this.bookId;
    }

    public String getUserAgent() {
        return this.userAgent;
    }
}
