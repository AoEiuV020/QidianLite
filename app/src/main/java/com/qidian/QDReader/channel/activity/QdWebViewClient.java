package com.qidian.QDReader.channel.activity;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.HashMap;
import java.util.Map;

/* compiled from: MainActivity */
class QdWebViewClient extends WebViewClient {
    final /* synthetic */ MainActivity mainActivity;

    QdWebViewClient(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str.toLowerCase().indexOf("qdlite://") > -1) {
            this.mainActivity.checkDownload(str);
        } else {
            Map hashMap = new HashMap();
            hashMap.put("referer", this.mainActivity.referer);
            hashMap.put("Pragma", "no-cache");
            hashMap.put("Cache-Control", "no-cache");
            webView.loadUrl(str, hashMap);
            this.mainActivity.referer = str;
        }
        return true;
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        webView.clearCache(true);
    }
}
