package com.qidian.QDReader.channel.util;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* compiled from: DownloadUtil */
public class DownloadUtil {
    public static void downloadApk(CallBack callBack) {
        Handler handler = new Handler(Looper.myLooper());
        String str = AppPath.downloadPath() + "QDReaderLite.apk";
        if (!new File(str).exists()) {
            UserActionUtil.set("qd_Lite_A02");
            new Thread(new DownloadRunnable2(handler, callBack, str)).start();
        }
    }

    public static boolean downloadTo(String url, String fileName, String folder) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            InputStream inputStream = httpURLConnection.getInputStream();
            byte[] a = read(inputStream);
            File file = new File(folder);
            if (!file.exists()) {
                file.mkdir();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(new File(file + File.separator + fileName));
            fileOutputStream.write(a);
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static byte[] read(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public static void installApk(String str) {
        File file = new File(str);
        if (file.exists()) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.parse("file://" + file.toString()), "application/vnd.android.package-archive");
            ApplicationContext.getApp().startActivity(intent);
            UserActionUtil.set("qd_Lite_A06");
        }
    }
}
