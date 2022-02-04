package com.cs7cs3.JourneySharing.db;

import com.cs7cs3.JourneySharing.entities.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<UserReview, String> {

}
