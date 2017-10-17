package com.qidian.QDReader.channel.util;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: FileUtil */
public class FileUtil {
    public static byte[] readFile(Context context, String str) {
        byte[] bArr = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (context != null) {
            try {
                if (!(context.getResources() == null || context.getResources().getAssets() == null)) {
                    InputStream open = context.getResources().getAssets().open(str);
                    byte[] bArr2 = new byte[8192];
                    while (true) {
                        try {
                            int read = open.read(bArr2);
                            if (read <= 0) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr2, 0, read);
                        } catch (OutOfMemoryError e) {
                            e.printStackTrace();
                        }
                    }
                    byteArrayOutputStream.flush();
                    bArr = byteArrayOutputStream.toByteArray();
                    open.close();
                    byteArrayOutputStream.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return bArr;
    }
}
