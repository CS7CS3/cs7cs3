package com.cs7cs3.JourneySharing.entities;

import javax.persistence.Embeddable;

import com.cs7cs3.JourneySharing.entities.base.validator.Validatable;

import lombok.Data;

@Data
@Embeddable
public class Location extends Validatable {
  public double latitude = 0.0;
  public double longitude = 0.0;

  public static final double EARTH_RADIUS = 6371000;

  public double Distance(Location x) {
    // calculate distance by longitude and latitude
    double lat1 = Math.toRadians(latitude);
    double lat2 = Math.toRadians(x.latitude);
    double lon1 = Math.toRadians(longitude);
    double lon2 = Math.toRadians(x.longitude);

    double dLat = lat2 - lat1;
    double dLon = lon2 - lon1;

    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
        + Math.cos(lat1) * Math.cos(lat2) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    return EARTH_RADIUS * c;
  }

  public void addLogitude(double x) {
    // move x meters on longitude
    this.longitude += x / (EARTH_RADIUS * Math.cos(Math.toRadians(this.latitude)));
  }

  public void addLatitude(double x) {
    // move x meters on latitude
    this.latitude += x / EARTH_RADIUS;
  }
}
