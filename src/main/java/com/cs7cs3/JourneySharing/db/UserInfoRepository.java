package com.cs7cs3.JourneySharing.db;

import com.cs7cs3.JourneySharing.entities.UserInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    @Query(value = "SELECT * FROM cs7cs3.user_info WHERE id= :id", nativeQuery = true)
    public Optional<UserInfo> findById1 (@Param("id") String id) ;
}
