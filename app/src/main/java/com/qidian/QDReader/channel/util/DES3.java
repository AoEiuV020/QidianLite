package com.qidian.QDReader.channel.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/* compiled from: DES3 */
public class DES3 {
    public static String encode(String str) {
        try {
            Key generateSecret = SecretKeyFactory.getInstance("desede").generateSecret(new DESedeKeySpec("JVYW9BWG7XJ98B3W34RT33B3".getBytes()));
            Cipher instance = Cipher.getInstance("desede/CBC/PKCS5Padding");
            instance.init(1, generateSecret, new IvParameterSpec("01234567".getBytes()));
            return Base64.encode(instance.doFinal(str.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
