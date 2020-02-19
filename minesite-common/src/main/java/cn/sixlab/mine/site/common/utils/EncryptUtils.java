package cn.sixlab.mine.site.common.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

public class EncryptUtils {
    private static BouncyCastleProvider provider = new BouncyCastleProvider();

    private static final int MAX_ENCRYPT_BLOCK = 117;

    static {
        Security.addProvider(provider);
    }

    public static String des(String content, String key) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes(StandardCharsets.UTF_8));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);
            byte[] result = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(result).replaceAll("\\s*", "");
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String aes(String content, String key) {
        try {
            byte[] text = content.getBytes(StandardCharsets.UTF_8);

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            byte[] ciphertext = cipher.doFinal(text);

            return Base64.getEncoder().encodeToString(ciphertext);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String rsaPublic(String content, String publicKey) {
        return rsa(content, KeyUtils.getPublicKey(publicKey));
    }

    public static String rsaPrivate(String content, String privateKey) {
        return rsa(content, KeyUtils.getPrivateKey(privateKey));
    }

    public static String rsa(String content, Key key) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            return Base64.getEncoder().encodeToString(cipher.doFinal(content.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String rsaAll(String content, Key key) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            int inputLength = content.getBytes(StandardCharsets.UTF_8).length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;
            byte[] cache;
            int i = 0;

            int length = inputLength - offSet;
            while (length > 0) {
                if (length > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8), offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8), offSet, length);
                }

                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;

                length = inputLength - offSet;
            }

            byte[] bytes = out.toByteArray();
            out.close();

            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
