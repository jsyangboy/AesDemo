package com.yibasan.aesdemo;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AesUtil {


    /**
     * 生成密钥
     * 自动生成base64 编码后的AES128位密钥
     *
     * @param keySize 密钥位数
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String generateKey(int keySize) throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(keySize);//要生成多少位，只需要修改这里即可128, 192或256
        SecretKey sk = kg.generateKey();
        byte[] b = sk.getEncoded();
        return parseByte2HexStr(b);
    }

    /**
     * AES 加密
     *
     * @param base64Key base64编码后的 AES key
     * @param text      待加密的字符串
     * @return 加密后的byte[] 数组
     * @throws Exception
     */
    public static byte[] encode(String base64Key, String text) throws Exception {
        byte[] key = parseHexStr2Byte(base64Key);
        SecretKeySpec sKeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
        return cipher.doFinal(text.getBytes());
    }

    public static String encode2(String base64Key, String text) throws Exception {
        byte[] key = parseHexStr2Byte(base64Key);
        SecretKeySpec sKeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
        return Base64.encodeToString(cipher.doFinal(text.getBytes("utf-8")), Base64.DEFAULT);
    }


    /**
     * AES解密
     *
     * @param base64Key base64编码后的 AES key
     * @param text      待解密的字符串
     * @return 解密后的byte[] 数组
     * @throws Exception
     */
    public static byte[] decode(String base64Key, byte[] text) throws Exception {
        byte[] key = parseHexStr2Byte(base64Key);
        SecretKeySpec sKeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, sKeySpec);
        return cipher.doFinal(text);
    }

    public static String decode2(String base64Key, String text) throws Exception {
        byte[] key = parseHexStr2Byte(base64Key);
        SecretKeySpec sKeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, sKeySpec);
        return new String(cipher.doFinal(Base64.decode(text, Base64.DEFAULT)),"utf-8");
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    private static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }


}
