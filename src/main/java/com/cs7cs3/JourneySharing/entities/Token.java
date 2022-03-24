package com.cs7cs3.JourneySharing.entities;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;
import com.cs7cs3.JourneySharing.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class Token extends Validatable {

  public static final long duration = Utils.timespan(30, 0, 0, 0);

  public static Token make(String userId) {
    return new Token(userId);
  }

  public static Token fromJson(String jsonStr) {
    ObjectMapper mapper = new ObjectMapper();

    try {
      return mapper.readValue(jsonStr, Token.class);
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    return null;
  }

  public String toJson() {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  public Token() {
  }

  private Token(String userId) {
    this.userId = userId;
    this.expire = Utils.timestamp() + duration;
  }

  public String userId;
  public long expire;
}
