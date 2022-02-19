package com.cs7cs3.JourneySharing.db;

import java.util.List;

import javax.transaction.Transactional;

import com.cs7cs3.JourneySharing.entities.Journey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JourneyRepository extends JpaRepository<Journey, String> {

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO `cs7cs3`.`journey_members` (`journey_id`, `user_id`) VALUES (:journeyId, :userId)", nativeQuery = true)
  public int join(@Param("journeyId") String journeyId, @Param("userId") String userId);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM `cs7cs3`.`journey_members` WHERE (`user_id` = :userId);", nativeQuery = true)
  public int exit(@Param("userId") String userId);

  @Query(value = "SELECT * FROM cs7cs3.journey WHERE (created_time > :ts and end_time = -1)", nativeQuery = true)
  public List<Journey> findByTimestamp(@Param("ts") long timestamp);

  @Query(value = "SELECT * FROM cs7cs3.journey WHERE end_time = -1", nativeQuery = true)
  public List<Journey> findAvailableJourneys();
}
