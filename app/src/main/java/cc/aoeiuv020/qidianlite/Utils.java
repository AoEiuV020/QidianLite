package cc.aoeiuv020.qidianlite;

import android.webkit.CookieManager;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 复制过来的工具类，
 * Created by AoEiuV020 on 2017.10.17-17:32:00.
 */

public class Utils {
    private static String md5Hex(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
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

    public static void setSign(String sign) {
        CookieManager instance = CookieManager.getInstance();
        instance.setAcceptCookie(true);
        instance.setCookie(".qidian.com", "QDSign=" + sign);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    public static String makeSign(String str) {
        String deviceId = "878788848187878";
        String id = deviceId;
        String sign;
        try {
            sign = md5Hex(str.toLowerCase());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String cookie = String.format(Locale.getDefault(), "QDLite!@#$%%|%d|%s|%s|1|1.0.0|1000147|%s", System.currentTimeMillis(), deviceId, id, sign);
        return cookie;
    }

    public static String des3(String str) {
        try {
            Key generateSecret = SecretKeyFactory.getInstance("desede").generateSecret(new DESedeKeySpec("JVYW9BWG7XJ98B3W34RT33B3".getBytes()));
            Cipher instance = Cipher.getInstance("desede/CBC/PKCS5Padding");
            instance.init(1, generateSecret, new IvParameterSpec("01234567".getBytes()));
            return base64(instance.doFinal(str.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static final char[] a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

    private static String base64(byte[] bArr) {
        int length = bArr.length;
        StringBuilder stringBuffer = new StringBuilder((bArr.length * 3) / 2);
        int i = length - 3;
        int i2 = 0;
        int i3 = 0;
        while (i3 <= i) {
            int i4 = (((bArr[i3] & 255) << 16) | ((bArr[i3 + 1] & 255) << 8)) | (bArr[i3 + 2] & 255);
            stringBuffer.append(a[(i4 >> 18) & 63]);
            stringBuffer.append(a[(i4 >> 12) & 63]);
            stringBuffer.append(a[(i4 >> 6) & 63]);
            stringBuffer.append(a[i4 & 63]);
            i4 = i3 + 3;
            i3 = i2 + 1;
            if (i2 >= 14) {
                stringBuffer.append(" ");
                i3 = 0;
            }
            i2 = i3;
            i3 = i4;
        }
        if (i3 == (length) - 2) {
            i3 = ((bArr[i3 + 1] & 255) << 8) | ((bArr[i3] & 255) << 16);
            stringBuffer.append(a[(i3 >> 18) & 63]);
            stringBuffer.append(a[(i3 >> 12) & 63]);
            stringBuffer.append(a[(i3 >> 6) & 63]);
            stringBuffer.append("=");
        } else if (i3 == (length) - 1) {
            i3 = (bArr[i3] & 255) << 16;
            stringBuffer.append(a[(i3 >> 18) & 63]);
            stringBuffer.append(a[(i3 >> 12) & 63]);
            stringBuffer.append("==");
        }
        return stringBuffer.toString();
    }
}
