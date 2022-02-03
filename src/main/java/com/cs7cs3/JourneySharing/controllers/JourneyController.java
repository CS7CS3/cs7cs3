package com.cs7cs3.JourneySharing.controllers;

import java.util.Optional;

import com.cs7cs3.JourneySharing.entities.Journey;
import com.cs7cs3.JourneySharing.entities.Request;
import com.cs7cs3.JourneySharing.entities.Response;
import com.cs7cs3.JourneySharing.entities.base.Empty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/journey")
public class JourneyController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  // private final JourneyRepository repository;

  // JourneyController(JourneyRepository repository) {
  // // this.repository = repository;
  // }

  @GetMapping("/{value}")
  public Response<Journey> get(@RequestBody Request<Empty> req) {
    // var res = repository.findById(id);
    // if (!res.isPresent()) {
    // return Response.makeResponse(false, "id does not exist", Optional.empty());
    // }
    // return Response.makeResponse(res.get());
    logger.info(req.toString());
    return Response.makeResponse(null);
  }

  @PostMapping
  public Response<Empty> post(@RequestBody Request<Journey> req) {
    // repository.save(journey);
    logger.info(req.toString());
    return Response.makeResponse(true, "", Optional.empty());
  }

  @PutMapping
  public Response<Empty> put(@RequestBody Request<Journey> req) {
    logger.info(req.toString());
    return Response.makeResponse(null);
  }

}
