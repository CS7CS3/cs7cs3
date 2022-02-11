package com.cs7cs3.JourneySharing.entities.request;

import java.util.Optional;

import com.cs7cs3.JourneySharing.entities.base.validator.IValidatable;
import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
public class Request<T extends IValidatable> extends Validatable {
  public static <U extends IValidatable> Request<U> makeRequest(String token, long timestamp, U u) {
    Request<U> resp = new Request<U>(token, timestamp, u);
    return resp;
  }

  public static <U extends IValidatable> Request<U> makeRequest(String token, long timestamp, Optional<U> u) {
    Request<U> resp = new Request<U>(token, timestamp, u);
    return resp;
  }

  public Request() {
  }

  public Request(String token, long timestamp, T playload) {
    this.token = token;
    this.timestamp = timestamp;
    if (playload != null) {
      this.payload = Optional.of(playload);
    }
  }

  public Request(String token, long timestamp, Optional<T> playload) {
    this.token = token;
    this.timestamp = timestamp;
    this.payload = playload;
  }

  public String token = "";
  public long timestamp = 0;
  public Optional<T> payload = Optional.empty();

  public T getPlayload() {
  return this.payload.orElse(null);
  }

  public void setPlayload(Optional<T> t) {
  if (t == null) {
  this.payload = Optional.empty();
  return;
  }

  if (!t.isPresent()) {
  this.payload = Optional.empty();
  return;
  }

  if (!t.get().validate()) {
  this.payload = Optional.empty();
  return;
  }

  this.payload = t;
  }
}
