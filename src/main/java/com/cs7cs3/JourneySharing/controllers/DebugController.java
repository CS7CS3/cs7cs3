package com.cs7cs3.JourneySharing.controllers;

import com.cs7cs3.JourneySharing.entities.messages.Request;
import com.cs7cs3.JourneySharing.entities.messages.journey.ExitJourneyRequest;
import com.cs7cs3.JourneySharing.entities.messages.journey.ExitJourneyResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/debug")
public class DebugController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @GetMapping
  Request<ExitJourneyResponse> test(@RequestBody Request<ExitJourneyRequest> req) {
    logger.info(req.toString());
    return null;
  }

}
