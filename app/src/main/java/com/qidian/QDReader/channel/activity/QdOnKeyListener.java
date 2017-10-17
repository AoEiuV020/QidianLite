package com.qidian.QDReader.channel.activity;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

/* compiled from: MainActivity */
class QdOnKeyListener implements OnKeyListener {
    final /* synthetic */ MainActivity mainActivity;

    QdOnKeyListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0 || i != 4 || !this.mainActivity.webView.canGoBack()) {
            return false;
        }
        this.mainActivity.webView.goBack();
        return true;
    }
}
