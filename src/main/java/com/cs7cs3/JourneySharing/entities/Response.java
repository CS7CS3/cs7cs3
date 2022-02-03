package com.cs7cs3.JourneySharing.entities;

import java.util.Optional;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
public class Response<T> extends Validatable{
  public static <U> Response<U> makeResponse(U u) {
    Response<U> resp = new Response<U>(true, "", u);
    return resp;
  }

  public static <U> Response<U> makeResponse(boolean success, String reason, U u) {
    Response<U> resp = new Response<U>(success, reason, u);
    return resp;
  }

  public static <U> Response<U> makeResponse(boolean success, String reason, Optional<U> u) {
    Response<U> resp = new Response<U>(success, reason, u);
    return resp;
  }

  public Response(boolean success, String reason, T t) {
    this.success = success;
    this.reason = reason;
    if (t != null) {
      this.payload = Optional.of(t);
    }
  }

  public Response(boolean success, String reason, Optional<T> t) {
    this.success = success;
    this.reason = reason;
    this.payload = t;
  }

  public boolean success = false;
  public String reason = "";
  public Optional<T> payload = Optional.empty();
}
