package com.cs7cs3.JourneySharing.db;

import java.util.List;

import com.cs7cs3.JourneySharing.entities.Journey;

import com.cs7cs3.JourneySharing.entities.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JourneyRepository extends JpaRepository<Journey, String> {

  @Query(value = "SELECT * FROM cs7cs3.journey WHERE (created_time > :ts and end_time = -1)", nativeQuery = true)
  public List<Journey> findByTimestamp(@Param("ts") long timestamp);

  @Query(value = "SELECT * FROM cs7cs3.journey WHERE end_time = -1", nativeQuery = true)
  public List<Journey> findAvailableJourneys();
}
