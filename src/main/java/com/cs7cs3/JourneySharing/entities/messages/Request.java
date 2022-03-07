package com.cs7cs3.JourneySharing.entities.messages;

import java.time.Instant;
import java.util.Optional;

import com.cs7cs3.JourneySharing.entities.base.validator.IValidatable;
import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;
import com.cs7cs3.JourneySharing.utils.Utils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import lombok.Data;

@Data
@JsonInclude(Include.NON_EMPTY)
public class Request<T extends IValidatable> extends Validatable {
  public static <U extends IValidatable> Request<U> make(String token, U u) {
    Request<U> resp = new Request<U>(token, Instant.now().getEpochSecond(), u);
    return resp;
  }

  public static <U extends IValidatable> Request<U> make(String token, Optional<U> u) {
    Request<U> resp = new Request<U>(token, Instant.now().getEpochSecond(), u);
    return resp;
  }

  public static <U extends IValidatable> Request<U> make(String token, long timestamp, U u) {
    Request<U> resp = new Request<U>(token, timestamp, u);
    return resp;
  }

  public static <U extends IValidatable> Request<U> make(String token, long timestamp, Optional<U> u) {
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

  public Pair<T, Optional<String>> test() {
    if (!validate()) {
      return new Pair<T, Optional<String>>(null, Optional.of("request validation failed"));
    }

    if (!this.payload.isPresent()) {
      return new Pair<T, Optional<String>>(null, Optional.of("empty payload"));
    }

    if (!Utils.validateToken(this.token)) {
      return new Pair<T, Optional<String>>(null, Optional.of("token validation failed"));
    }

    var payload = this.payload.get();
    if (!payload.validate()) {
      return new Pair<T, Optional<String>>(null, Optional.of("payload schema validation failed"));
    }

    return new Pair<T, Optional<String>>(payload, Optional.empty());
  }

  public Optional<String> testIgnorePayloadCheck() {
    if (!validate()) {
      return Optional.of("request validation failed");
    }

    if (!Utils.validateToken(this.token)) {
      return Optional.of("token validation failed");
    }

    return Optional.empty();
  }

  public String token = "";
  public long timestamp = 0;
  public Optional<T> payload = Optional.empty();

}
