package com.cs7cs3.JourneySharing.utils;

import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.cs7cs3.JourneySharing.entities.Token;

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

  public static boolean validateToken(String token) {
    return true;
  }

  public static String makeToken(String userId) {
    var token = Token.make(userId);
    byte result[] = encrypt(token.toJson().getBytes());
    return Base64.encodeBase64String(result);
  }

  public static String nextToken(String tokenStr) {
    byte result[] = decrypt(Base64.decodeBase64(tokenStr));
    var token = Token.fromJson(new String(result));
    token.refresh();
    result = encrypt(token.toJson().getBytes());
    return Base64.encodeBase64String(result);
  }

  public static String getIdByToken(String tokenStr) {
    byte result[] = decrypt(Base64.decodeBase64(tokenStr));
    var token = Token.fromJson(new String(result));
    return token.userId;
  }

  public static long getTimeByToken(String tokenStr) {
    byte result[] = decrypt(Base64.decodeBase64(tokenStr));
    var token = Token.fromJson(new String(result));
    return token.expire;
  }

}
