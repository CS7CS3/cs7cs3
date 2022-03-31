package com.cs7cs3.JourneySharing.db;

import com.cs7cs3.JourneySharing.entities.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, String> {

  @Query(value = "SELECT COUNT(*) > 0 AS result FROM `cs7cs3`.`user_info` WHERE (`username` = :username)", nativeQuery = true)
  public int exist(@Param("username") String username);

  @Query(value = """
      SELECT
        (COUNT(*) > 0) AS Result
      FROM
        `cs7cs3`.`account`
      WHERE
        id = (SELECT
                id
            FROM
                `cs7cs3`.`user_info`
            WHERE
                username = :username)
        AND password = :password
      """, nativeQuery = true)
  public int testPassword(@Param("username") String username, @Param("password") String password);

  @Query(value = "SELECT `public_key` FROM `cs7cs3`.`account` WHERE (`id` = :userId)", nativeQuery = true)
  public String getPublicKey(@Param("userId") String userId);

  @Query(value = "SELECT `private_key` FROM `cs7cs3`.`account` WHERE (`id` = :userId)", nativeQuery = true)
  public String getPrivateKey(@Param("userId") String userId);
}
