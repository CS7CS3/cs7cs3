package com.cs7cs3.JourneySharing.entities.messages;

import java.util.Optional;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
public class Response<T> extends Validatable {
  public static <U> Response<U> makeError(String reason) {
    Response<U> resp = new Response<U>(false, reason, null, Optional.empty());
    return resp;
  }

  public static <U> Response<U> make(String token) {
    Response<U> resp = new Response<U>(true, null, token, Optional.empty());
    return resp;
  }

  public static <U> Response<U> make(String token, U u) {
    Response<U> resp = new Response<U>(true, null, token, u);
    return resp;
  }

  public static <U> Response<U> make(String token, Optional<U> u) {
    Response<U> resp = new Response<U>(true, null, token, u);
    return resp;
  }

  public static <U> Response<U> make(boolean success, String reason, String token, U u) {
    Response<U> resp = new Response<U>(success, reason, token, u);
    return resp;
  }

  public static <U> Response<U> make(boolean success, String reason, String token, Optional<U> u) {
    Response<U> resp = new Response<U>(success, reason, token, u);
    return resp;
  }

  public Response(boolean success, String reason, String token, T t) {
    this.success = success;
    this.reason = reason;
    this.token = token;
    if (t != null) {
      this.payload = Optional.of(t);
    }
  }

  public Response(boolean success, String reason, String token, Optional<T> t) {
    this.success = success;
    this.reason = reason;
    this.token = token;
    this.payload = t;
  }

  public boolean success = false;
  public String reason = "";
  public String token = "";
  public Optional<T> payload = Optional.empty();
}
