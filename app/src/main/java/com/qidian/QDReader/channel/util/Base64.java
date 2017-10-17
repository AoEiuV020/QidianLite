package com.qidian.QDReader.channel.util;

/* compiled from: DES3 */
public class Base64 {
    public static final char[] a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

    public static String encode(byte[] bArr) {
        int length = bArr.length;
        StringBuffer stringBuffer = new StringBuffer((bArr.length * 3) / 2);
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
        if (i3 == (0 + length) - 2) {
            i3 = ((bArr[i3 + 1] & 255) << 8) | ((bArr[i3] & 255) << 16);
            stringBuffer.append(a[(i3 >> 18) & 63]);
            stringBuffer.append(a[(i3 >> 12) & 63]);
            stringBuffer.append(a[(i3 >> 6) & 63]);
            stringBuffer.append("=");
        } else if (i3 == (0 + length) - 1) {
            i3 = (bArr[i3] & 255) << 16;
            stringBuffer.append(a[(i3 >> 18) & 63]);
            stringBuffer.append(a[(i3 >> 12) & 63]);
            stringBuffer.append("==");
        }
        return stringBuffer.toString();
    }
}
