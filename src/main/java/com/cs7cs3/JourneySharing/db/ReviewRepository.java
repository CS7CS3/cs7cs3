package com.cs7cs3.JourneySharing.db;

import com.cs7cs3.JourneySharing.entities.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<UserReview, String> {

    @Modifying
    @Query(value = "INSERT ignore INTO cs7cs3.user_review (review_id, user_id,rating,content,anonymous,reviewee_id) VALUES (:reId, :userId, :rating, :content,:ano,:reeId)", nativeQuery = true)
    public void insert(@Param("reId") String reId, @Param("userId") String userId, @Param("rating") Double rating,@Param("content") String content,@Param("ano") boolean ano,@Param("reeId") String reeId);

    @Query(value = "SELECT content,rating FROM cs7cs3.user_review WHERE user_id = :userId", nativeQuery = true)
    public Optional<UserReview> findByUser(@Param("userId") String userId);

}
