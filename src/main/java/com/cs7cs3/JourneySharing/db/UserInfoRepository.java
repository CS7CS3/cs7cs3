package com.cs7cs3.JourneySharing.db;

import java.util.Optional;

import com.cs7cs3.JourneySharing.entities.UserInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    @Query(value = "SELECT * FROM cs7cs3.user_info WHERE id= :id", nativeQuery = true)
    public Optional<UserInfo> findById1(@Param("id") String id);

    @Query(value = "SELECT id FROM cs7cs3.user_info WHERE username = :username", nativeQuery = true)
    public String getIdByUsername(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO `cs7cs3`.`user_info_histories` (`user_id`, `journey_id`) VALUES (:userId, :journeyId);", nativeQuery = true)
    public void addHistory(@Param("userId") String userId, @Param("journeyId") String journeyId);


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO `cs7cs3`.`user_info_reviews` (`user_id`, `review_id`) VALUES (:revieweeId, :reviewId);", nativeQuery = true)
    public void addReview(@Param("revieweeId") String revieweeId, @Param("reviewId") String reviewId);


    @Query(value = "SELECT avatar FROM cs7cs3.user_info WHERE id= :userId", nativeQuery = true)
    public String getAvatar(@Param("userId") String userId);
}
