package com.cs7cs3.JourneySharing.db;

import com.cs7cs3.JourneySharing.entities.Journey;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JourneyRepository extends JpaRepository<Journey, String> {

}