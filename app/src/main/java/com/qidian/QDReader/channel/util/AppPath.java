package com.qidian.QDReader.channel.util;

import android.app.Application;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

/* compiled from: AppPath */
public class AppPath {
    public static String name = "QDReader";

    public static String base() {
        String str = "/" + name + "/";
        Application app = ApplicationContext.getApp();
        File filesDir = app.getFilesDir();
        String str2 = (filesDir == null ? "/data/data/" + app.getPackageName() + "/files" : filesDir.getAbsolutePath()) + str;
        try {
            CharSequence externalStorageState = Environment.getExternalStorageState();
            if (!TextUtils.isEmpty(externalStorageState) && "mounted".equals(externalStorageState)) {
                filesDir = Environment.getExternalStorageDirectory();
                if (filesDir != null && filesDir.exists() && filesDir.canWrite()) {
                    str2 = filesDir.getAbsolutePath() + str;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str2;
    }

    public static String path(String str) {
        File file = new File(base() + "/" + str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath() + "/";
    }

    public static String downloadPath() {
        return path("download");
    }
}
