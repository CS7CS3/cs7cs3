package com.cs7cs3.JourneySharing.db;

import java.util.Optional;

import javax.transaction.Transactional;

import com.cs7cs3.JourneySharing.entities.UserInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    @Query(value = "SELECT * FROM cs7cs3.user_info WHERE id= :id", nativeQuery = true)
    public Optional<UserInfo> findById1(@Param("id") String id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO `cs7cs3`.`user_info_histories` (`user_id`, `journey_id`) VALUES (:userId, :journeyId);", nativeQuery = true)
    public void addHistory(@Param("userId") String userId, @Param("journeyId") String journeyId);
}
