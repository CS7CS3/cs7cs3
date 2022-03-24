package com.cs7cs3.JourneySharing.utils;

import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

public class Utils {

  private static final byte key[] = "xQInSbvNhzJu4Zt1iGqFDag8rPdKpeEX".getBytes();

  public static String makeUUID() {
    return UUID.randomUUID().toString();
  }

  public static long timestamp() {
    return System.currentTimeMillis() / 1000;
  }

  public static long timespan(int d, int h, int m, int s) {
    return s + m * 60 + h * 60 * 60 + d * 60 * 60 * 24;
  }

  public static byte[] encrypt(byte[] in) {
    try {
      SecretKeySpec sKey = new SecretKeySpec(key, "AES");
      var cipher = Cipher.getInstance("AES");
      cipher.init(Cipher.ENCRYPT_MODE, sKey);
      byte result[] = cipher.doFinal(in);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  public static byte[] decrypt(byte[] in) {
    try {
      SecretKeySpec sKey = new SecretKeySpec(key, "AES");
      var cipher = Cipher.getInstance("AES");
      cipher.init(Cipher.DECRYPT_MODE, sKey);
      byte result[] = cipher.doFinal(in);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  // public static String makeToken(String userId) {
  // var token = Token.make(userId);
  // return Base64.encodeBase64String(encrypt(token.toString().getBytes()));
  // }

  // public static String nextToken(String tokenStr) {
  // byte result[] = decrypt(Base64.decodeBase64(tokenStr));
  // var token = Token.fromJson(new String(result));
  // token.expire = Utils.timestamp() + Token.duration;

  // var jsonStr = token.toString();

  // result = encrypt(jsonStr.getBytes());

  // return Base64.encodeBase64String(result);
  // }

  // public static String getIdByToken(String tokenStr) {
  // byte result[] = decrypt(Base64.decodeBase64(tokenStr));
  // var token = Token.fromJson(new String(result));
  // return token.userId;
  // }

  public static boolean validateToken(String token) {
    return true;
  }

  public static String makeToken(String userId) {
    try {
      SecretKeySpec sKey = new SecretKeySpec(key, "AES");

      Cipher cipher = Cipher.getInstance("AES");
      cipher.init(Cipher.ENCRYPT_MODE, sKey);
      byte result[] = cipher.doFinal(userId.getBytes());

      return Base64.encodeBase64String(result);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return "";
  }

  public static String nextToken(String token) {
    return token;
  }

  public static String getIdByToken(String token) {
    try {
      SecretKeySpec sKey = new SecretKeySpec(key, "AES");

      Cipher cipher = Cipher.getInstance("AES");
      cipher.init(Cipher.DECRYPT_MODE, sKey);

      byte decode[] = Base64.decodeBase64(token);
      byte result[] = cipher.doFinal(decode);

      return new String(result);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return "";
  }

}
