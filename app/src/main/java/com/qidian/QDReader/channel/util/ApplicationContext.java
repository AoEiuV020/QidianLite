package com.qidian.QDReader.channel.util;

import android.app.Application;

/* compiled from: ApplicationContext */
public class ApplicationContext {
    public static Application app;

    public static Application getApp() {
        return app;
    }

    public static void setApp(Application application) {
        ApplicationContext.app = application;
    }
}
