package com.qidian.QDReader.channel.util;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

/* compiled from: NetworkUtil */
public class NetworkUtil {
    public static Boolean a() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) ApplicationContext.getApp().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return Boolean.valueOf(false);
        }
        return Boolean.valueOf(activeNetworkInfo.isAvailable());
    }

    public static int b() {
        ConnectivityManager connectivityManager = (ConnectivityManager) ApplicationContext.getApp().getSystemService("connectivity");
        State state = connectivityManager.getNetworkInfo(1).getState();
        if (state == State.CONNECTED || state == State.CONNECTING) {
            return 1;
        }
        State state2 = connectivityManager.getNetworkInfo(0).getState();
        return (state2 == State.CONNECTED || state2 == State.CONNECTING) ? 5 : 0;
    }
}
