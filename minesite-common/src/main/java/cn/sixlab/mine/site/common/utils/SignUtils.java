package cn.sixlab.mine.site.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class SignUtils {
    private static BouncyCastleProvider provider = new BouncyCastleProvider();

    static {
        Security.addProvider(provider);
    }

    private static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String md5(String str) {
        String re_md5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest();

            int i;

            StringBuilder buf = new StringBuilder();
            for (byte value : bytes) {
                i = value;
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }

    public static String md5withRSA(String content, String privateKey) {
        return md5withRSA(content, KeyUtils.getPrivateKey(privateKey));
    }

    public static String md5withRSA(String content, PrivateKey key) {
        try {
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initSign(key);
            signature.update(content.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(signature.sign());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String sha(String data) throws Exception {
        if (StringUtils.isEmpty(data)) {
            return "";
        }
        MessageDigest sha = MessageDigest.getInstance("SHA");
        sha.update(data.getBytes());
        byte[] bytes = sha.digest();
        return byteArrayToHexString(bytes);
    }

    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(byteToHexString(aByte));
        }
        return sb.toString();
    }

    private static String byteToHexString(byte b) {
        int ret = b;
        if (ret < 0) {
            ret += 256;
        }
        int m = ret / 16;
        int n = ret % 16;
        return hexDigits[m] + hexDigits[n];
    }

    public static String sh1Base64(String data) {
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            byte[] sha1Bytes = sha1.digest(data.getBytes());
            String base64Str = "";
            if (sha1Bytes != null) {
                base64Str = Base64.getEncoder().encodeToString(sha1Bytes);
            }
            return base64Str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String sha1WithRSA(String content, String privateKey) {
        return sha1WithRSA(content, KeyUtils.getPrivateKey(privateKey));
    }

    public static String sha1WithRSA(String content, PrivateKey key) {
        try {
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(key);
            signature.update(content.getBytes(StandardCharsets.UTF_8));
            byte[] signed = signature.sign();
            return Base64.getEncoder().encodeToString(signed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean verifyMd5withRSA(String content, String signText, String publicKey) {
        return verifyMd5withRSA(content, signText, KeyUtils.getPublicKey(publicKey));
    }

    public static boolean verifyMd5withRSA(String content, String signText, PublicKey key) {
        try {
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initVerify(key);
            signature.update(content.getBytes(StandardCharsets.UTF_8));

            byte[] bytes = Base64.getDecoder().decode(signText);

            return signature.verify(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean verifySha1withRSA(String content, String signText, String publicKey) {
        return verifySha1withRSA(content, signText, KeyUtils.getPublicKey(publicKey));
    }

    public static boolean verifySha1withRSA(String content, String signText, PublicKey key) {
        try {
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initVerify(key);
            signature.update(content.getBytes(StandardCharsets.UTF_8));

            byte[] bytes = Base64.getDecoder().decode(signText);

            return signature.verify(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
