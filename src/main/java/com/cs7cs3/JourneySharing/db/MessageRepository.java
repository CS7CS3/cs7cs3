package com.cs7cs3.JourneySharing.db;

import java.util.List;

import com.cs7cs3.JourneySharing.entities.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<Message, String> {

  @Query(value = """
      SELECT
        *
      FROM
        cs7cs3.message
      WHERE
        sender = :userId OR receiver = :userId
      ORDER BY timestamp DESC
      LIMIT :len OFFSET :from
      """, nativeQuery = true)
  public List<Message> getMessagesByUserIdOrderByTimestamp(@Param("userId") String userId, @Param("from") int from,
      @Param("len") int len);
}
