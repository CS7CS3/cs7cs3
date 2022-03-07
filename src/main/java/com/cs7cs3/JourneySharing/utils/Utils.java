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

  public static boolean validateToken(String token) {
    return true;
  }
}
