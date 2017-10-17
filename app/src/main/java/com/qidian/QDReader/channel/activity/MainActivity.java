package com.qidian.QDReader.channel.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.qidian.QDReader.channel.util.AppInfo;
import com.qidian.QDReader.channel.util.AppPath;
import com.qidian.QDReader.channel.util.DES3;
import com.qidian.QDReader.channel.util.DownloadUtil;
import com.qidian.QDReader.channel.util.NetworkUtil;
import com.qidian.QDReader.channel.util.UserActionUtil;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import cc.aoeiuv020.qidianlite.R;

public class MainActivity extends Activity {
    public String liteUrl = "http://oam.qidian.com/lite/";
    public WebView webView;
    public String referer;
    public boolean flag;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.activity_main);
        initUrl();
        String bookId = AppInfo.getInstance().getBookId();
        String url = "http://m.qidian.com";
        if (!TextUtils.isEmpty(bookId)) {
            url = this.liteUrl + bookId;
        }
        String sign = makeSign(url);
        Log.d("QDLite", "cookie ==> " + sign);
        sign = Uri.encode(DES3.encode(sign).replace(" ", ""));
        Log.d("QDLite", "qdSign ==> " + sign);
        initWebView(bookId, url, sign);
        QDReaderLiteApk(false, false);
    }

    protected void onDestroy() {
        super.onDestroy();
        try {
            CookieSyncManager.getInstance().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserActionUtil.set("qd_Lite_A08");
    }

    public void initUrl() {
        this.liteUrl = "http://m.qidian.com/lite/";
    }

    public void QDReaderLiteApk(boolean z, boolean z2) {
        String str = AppPath.downloadPath() + "QDReaderLite.apk";
        if (new File(str).exists()) {
            if (z) {
                DownloadUtil.installApk(str);
            }
        } else if (!NetworkUtil.a().booleanValue()) {
        } else {
            if (z2 || NetworkUtil.b() == 1) {
                this.flag = true;
                DownloadUtil.downloadApk(new QdCallBack(this, z));
            }
        }
    }

    public void checkDownload(String str) {
        if (!"checkDownload".equals(str.replace("qdlite://", ""))) {
            return;
        }
        if (this.flag) {
            Toast.makeText(this, "正在获取中...", Toast.LENGTH_LONG).show();
        } else {
            QDReaderLiteApk(true, true);
        }
    }

    public void initWebView(String bookId, String url, String sign) {
        this.webView = (WebView) findViewById(R.id.wvBook);
        this.webView.setWebViewClient(new QdWebViewClient(this));
        this.webView.setOnKeyListener(new QdOnKeyListener(this));
        WebSettings settings = this.webView.getSettings();
        if (settings != null) {
            settings.setSupportZoom(true);
            settings.setBuiltInZoomControls(true);
            settings.setJavaScriptEnabled(true);
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
            settings.setDomStorageEnabled(true);
            settings.setDatabaseEnabled(true);
            settings.setAppCacheEnabled(true);
            settings.setUseWideViewPort(true);
            settings.setLoadWithOverviewMode(true);
            settings.setUserAgentString(settings.getUserAgentString() + AppInfo.getInstance().getUserAgent());
            settings.setAppCacheMaxSize(1);
            if (VERSION.SDK_INT >= 16) {
                settings.setAllowUniversalAccessFromFileURLs(true);
            }
        }
        try {
            setSign(sign);
            this.webView.loadUrl(url);
            this.referer = url;
            Map hashMap = new HashMap();
            hashMap.put("bookId", bookId);
            UserActionUtil.set("qd_Lite_A01", hashMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String makeSign(String str) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("QDLite!@#$%");
            stringBuffer.append("|");
            stringBuffer.append(System.currentTimeMillis());
            stringBuffer.append("|");
            stringBuffer.append(AppInfo.getInstance().getDeviceId());
            stringBuffer.append("|");
            stringBuffer.append(AppInfo.getInstance().getId());
            stringBuffer.append("|");
            stringBuffer.append(AppInfo.getInstance().getVersion());
            stringBuffer.append("|");
            stringBuffer.append("1.0.0");
            stringBuffer.append("|");
            stringBuffer.append("1000147");
            stringBuffer.append("|");
            stringBuffer.append(md5Hex(str.toLowerCase()));
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void setSign(String sign) {
        try {
            CookieManager instance = CookieManager.getInstance();
            instance.setAcceptCookie(true);
            if (VERSION.SDK_INT >= 21) {
                instance.setAcceptThirdPartyCookies(this.webView, true);
                instance.acceptThirdPartyCookies(this.webView);
            }
            instance.setCookie(".qidian.com", "QDSign=" + sign);
            CookieSyncManager.getInstance().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String md5Hex(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
        StringBuilder stringBuilder = new StringBuilder(digest.length * 2);
        for (byte b : digest) {
            if ((b & 255) < 16) {
                stringBuilder.append("0");
            }
            stringBuilder.append(Integer.toHexString(b & 255));
        }
        return stringBuilder.toString();
    }
}
