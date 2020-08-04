package tech.minesoft.minesite.core.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyUtils {

    private static BouncyCastleProvider provider = new BouncyCastleProvider();

    static {
        Security.addProvider(provider);
    }

    public static PrivateKey getPrivateKey(String privateKey) {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey.getBytes(StandardCharsets.UTF_8)));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static PublicKey getPublicKey(String publicKey) {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey.getBytes(StandardCharsets.UTF_8)));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static KeyPair genRSAKeyPair(int width) {
        try {
            //获取密钥对生成器实例
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA", "BC");
            //设定RSA位宽
            kpg.initialize(width);
            return kpg.generateKeyPair();
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
        }
        // return new JcaPGPKeyPair(PGPPublicKey.RSA_GENERAL, kp, new Date());
        // 返回根据日期，密钥对生成的PGP密钥对
        return null;
    }

    public static String genPublicKey(KeyPair kp) {
        PemObject pemObject = new PemObject("RSA PUBLIC KEY", kp.getPublic().getEncoded());
        return getKeyString(pemObject);
    }

    public static String genPrivateKey(KeyPair kp) {
        PemObject pemObject = new PemObject("RSA PRIVATE KEY", kp.getPrivate().getEncoded());
        return getKeyString(pemObject);
    }

    private static String getKeyString(PemObject pemObject) {
        String result = null;
        try {
            StringWriter stringWriter = new StringWriter();
            PemWriter pemWriter = new PemWriter(stringWriter);
            pemWriter.writeObject(pemObject);
            pemWriter.close();
            result = stringWriter.toString();
            stringWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
