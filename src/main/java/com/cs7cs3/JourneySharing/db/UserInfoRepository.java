package com.cs7cs3.JourneySharing.db;

import com.cs7cs3.JourneySharing.entities.UserInfo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
}
