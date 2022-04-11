package com.cs7cs3.JourneySharing.entities;

import javax.persistence.Embeddable;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
@Embeddable
public class Location extends Validatable {
  public double latitude = 0.0;
  public double longitude = 0.0;

  public double Distance(Location x) {
    double diff_longitude = this.longitude - x.longitude;
    double diff_latitude = this.latitude - x.latitude;

    double diff_in_meter_longitude = 111194.926_644_558_737 * Math.cos(diff_longitude);
    double diff_in_meter_latitude = 111194.926_644_558_737 * Math.cos(diff_latitude);

    return Math.sqrt(Math.pow(diff_in_meter_longitude, 2)
        + Math.pow(diff_in_meter_latitude, 2));
  }

  public void addLogitude(double x) {
    double longitude = Math.acos(x / 111194.926_644_558_737);
    this.longitude += longitude;
  }

  public void addLatitude(double x) {
    double latitude = Math.acos(x / 111194.926_644_558_737);
    this.latitude += latitude;
  }
}
