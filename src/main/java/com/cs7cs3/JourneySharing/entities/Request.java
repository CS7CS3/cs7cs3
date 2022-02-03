package com.cs7cs3.JourneySharing.entities;

import java.util.Optional;

import com.cs7cs3.JourneySharing.entities.base.Timestamp;
import com.cs7cs3.JourneySharing.entities.base.Token;
import com.cs7cs3.JourneySharing.entities.base.validator.IValidatable;
import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
public class Request<T extends IValidatable> extends Validatable {
  public static <U extends IValidatable> Request<U> makeRequest(Token token, Timestamp timestamp, U u) {
    Request<U> resp = new Request<U>(token, timestamp, u);
    return resp;
  }

  public static <U extends IValidatable> Request<U> makeRequest(Token token, Timestamp timestamp, Optional<U> u) {
    Request<U> resp = new Request<U>(token, timestamp, u);
    return resp;
  }

  public Request() {
  }

  public Request(Token token, Timestamp timestamp, T playload) {
    this.token = token;
    this.timestamp = timestamp;
    if (playload != null) {
      this.payload = Optional.of(playload);
    }
  }

  public Request(Token token, Timestamp timestamp, Optional<T> playload) {
    this.token = token;
    this.timestamp = timestamp;
    this.payload = playload;
  }

  public Token token = new Token();
  public Timestamp timestamp = new Timestamp();
  public Optional<T> payload = Optional.empty();

  public String getToken() {
    return token.value;
  }

  public void setToken(String value) {
    token.value = value;
  }

  public long getTimestamp() {
    return timestamp.value;
  }

  public void setTimestamp(long value) {
    timestamp.value = value;
  }

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
