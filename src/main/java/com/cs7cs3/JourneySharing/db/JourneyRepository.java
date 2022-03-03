package com.cs7cs3.JourneySharing.db;

import java.util.List;

import javax.transaction.Transactional;

import com.cs7cs3.JourneySharing.entities.Journey;
import com.cs7cs3.JourneySharing.entities.Journey.JourneyStatus;
import com.cs7cs3.JourneySharing.entities.Journey.UserStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JourneyRepository extends JpaRepository<Journey, String> {

  @Query(value = "SELECT `journey_id` FROM cs7cs3.journey_members WHERE user_id = :userId", nativeQuery = true)
  public String getJourneyIdByUserId(@Param("userId") String userId);

  @Query(value = "SELECT `id` FROM cs7cs3.journey WHERE host = :hostId", nativeQuery = true)
  public String getJourneyIdByHostId(@Param("hostId") String hostId);

  @Query(value = "SELECT `status` FROM cs7cs3.journey WHERE (`id` = :journeyId)", nativeQuery = true)
  public JourneyStatus getJourneyStatusByJourneyId(@Param("journeyId") String journeyId);

  @Query(value = """
      SELECT
        `status`
      FROM
        cs7cs3.journey
      WHERE
        (id = (SELECT
                `journey_id`
            FROM
                cs7cs3.journey_members
            WHERE
                user_id = :userId))
      """, nativeQuery = true)
  public JourneyStatus getJourneyStatusByUserId(@Param("userId") String userId);

  @Query(value = "SELECT `status` FROM cs7cs3.journey_members WHERE user_id = :userId", nativeQuery = true)
  public UserStatus getUserStatusByUserId(@Param("userId") String userId);

  @Modifying
  @Transactional
  @Query(value = "INSERT INTO `cs7cs3`.`journey_members` (`journey_id`, `user_id`, `status`) VALUES (:journeyId, :userId, :status)", nativeQuery = true)
  public int join(@Param("journeyId") String journeyId, @Param("userId") String userId,
      @Param("status") int status);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM `cs7cs3`.`journey_members` WHERE (`user_id` = :userId)", nativeQuery = true)
  public int exit(@Param("userId") String userId);

  @Query(value = "SELECT * FROM cs7cs3.journey WHERE (created_time > :ts and end_time = -1)", nativeQuery = true)
  public List<Journey> findByTimestamp(@Param("ts") long timestamp);

  @Query(value = "SELECT * FROM cs7cs3.journey WHERE end_time = -1", nativeQuery = true)
  public List<Journey> findAvailableJourneys();

  @Modifying
  @Transactional
  @Query(value = "UPDATE cs7cs3.journey_members SET journey_members.status = :status WHERE user_id = :userId", nativeQuery = true)
  public void setUserStatus(@Param("userId") String userId, @Param("status") int status);

  @Modifying
  @Transactional
  @Query(value = "UPDATE `cs7cs3`.`journey_members` SET `status` = :status WHERE (`journey_id` = :journeyId);", nativeQuery = true)
  public void setUserStatusByJourneyId(@Param("journeyId") String journeyId, @Param("status") int status);

  @Modifying
  @Transactional
  @Query(value = "UPDATE cs7cs3.journey SET status = :status WHERE id = :journeyId", nativeQuery = true)
  public void setJourneyStatus(@Param("journeyId") String journeyId, @Param("status") int status);

  @Query(value = "SELECT status FROM cs7cs3.journey_members WHERE journey_id = :journeyId", nativeQuery = true)
  public List<UserStatus> getUserStatusByJourneyId(@Param("journeyId") String journeyId);

  @Query(value = "SELECT user_id FROM cs7cs3.journey_members WHERE journey_id = :journeyId", nativeQuery = true)
  public List<String> getUserIdByJourneyId(@Param("journeyId") String journeyId);

}
