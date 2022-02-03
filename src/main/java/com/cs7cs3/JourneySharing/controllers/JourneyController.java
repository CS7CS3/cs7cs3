package com.cs7cs3.JourneySharing.controllers;

import java.util.Optional;

import com.cs7cs3.JourneySharing.db.JourneyRepository;
import com.cs7cs3.JourneySharing.entities.Journey;
import com.cs7cs3.JourneySharing.entities.Request;
import com.cs7cs3.JourneySharing.entities.Response;
import com.cs7cs3.JourneySharing.entities.base.Empty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  private JourneyRepository repository;

  @GetMapping("/{value}")
  public Response<Journey> get(@RequestBody Request<Empty> req, String id) {
    logger.info(req.toString());
    if (!req.validate()) {
      logger.error("?");
      return Response.makeResponse(false, "?", "", Optional.empty());
    }

    if (/* var token = req.get().token; validate(token) */ false) {
      logger.error("?");
      return Response.makeResponse(false, "?", "", Optional.empty());
    }

    var res = repository.findById(id);
    if (!res.isPresent()) {
      return Response.makeResponse(false, "id does not exist", /* next_token(token) */ "", Optional.empty());
    }
    return Response.makeResponse(/* next_token(token) */ "", res.get());
  }

  @PostMapping
  public Response<Empty> post(@RequestBody Request<Journey> req) {
    logger.info(req.toString());

    repository.save(req.payload.get());
    return Response.makeResponse(true, "", /* next_token(token) */ "", Optional.empty());
  }

  @PutMapping
  public Response<Empty> put(@RequestBody Request<Journey> req) {
    logger.info(req.toString());

    repository.save(req.payload.get());
    return Response.makeResponse(true, "", /* next_token(token) */ "", Optional.empty());
  }

}
