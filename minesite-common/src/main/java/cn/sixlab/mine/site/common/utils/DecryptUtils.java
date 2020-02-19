package cn.sixlab.mine.site.common.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

public class DecryptUtils {
    private static BouncyCastleProvider provider = new BouncyCastleProvider();

    static {
        Security.addProvider(provider);
    }

    private static final int MAX_DECRYPT_BLOCK = 128;

    public static String des(String content, String key) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, random);
            byte[] source = Base64.getDecoder().decode(content);
            byte[] result = cipher.doFinal(source);
            return new String(result);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String aes(String content, String key) {
        String encryptValue = "";
        try {
            byte[] plaintext = content.getBytes(StandardCharsets.UTF_8);

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

            cipher.init(Cipher.DECRYPT_MODE, keySpec);

            byte[] bys = cipher.doFinal(Base64.getDecoder().decode(plaintext));

            encryptValue = new String(bys, StandardCharsets.UTF_8);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return encryptValue;
    }

    public static String rsaPrivate(String content, String privateKey) {
        return rsa(content, KeyUtils.getPrivateKey(privateKey));
    }

    public static String rsaPublic(String content, String publicKey) {
        return rsa(content, KeyUtils.getPublicKey(publicKey));
    }

    public static String rsa(String content, Key key) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] decode = Base64.getDecoder().decode(content.getBytes(StandardCharsets.UTF_8));
            byte[] result = cipher.doFinal(decode);

            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String rsaAll(String content, Key key) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            ByteArrayOutputStream writer = new ByteArrayOutputStream();
            //rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
            byte[] buffer = new byte[MAX_DECRYPT_BLOCK];
            int length;

            byte[] decode = Base64.getDecoder().decode(content.getBytes(StandardCharsets.UTF_8));
            InputStream is = new ByteArrayInputStream(decode);
            while ((length = is.read(buffer)) != -1) {
                byte[] block;

                if (buffer.length == length) {
                    block = buffer;
                } else {
                    block = new byte[length];
                    System.arraycopy(buffer, 0, block, 0, length);
                }

                writer.write(cipher.doFinal(block));
            }

            byte[] bytes = writer.toByteArray();
            writer.close();

            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
