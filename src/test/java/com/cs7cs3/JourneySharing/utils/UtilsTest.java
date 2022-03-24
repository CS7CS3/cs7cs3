package com.cs7cs3.JourneySharing.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UtilsTest {

  @Test
  void testToken() {
    String userId = "66670c47-2103-4e4d-a980-96364094036c";
    String token = Utils.makeToken(userId);
    System.out.println(token);
    String newUserId = Utils.getIdByToken(token);

    assertEquals(userId, newUserId);
  }

}
