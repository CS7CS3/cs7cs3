package com.cs7cs3.JourneySharing.db;

import com.cs7cs3.JourneySharing.entities.Account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

}

