package com.cs7cs3.JourneySharing.controllers;

import java.util.Optional;

import com.cs7cs3.JourneySharing.db.JourneyRepository;
import com.cs7cs3.JourneySharing.entities.Journey;
import com.cs7cs3.JourneySharing.entities.Response;
import com.cs7cs3.JourneySharing.entities.base.JourneyId;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/journey")
public class JourneyController {

  private final JourneyRepository repository;

  JourneyController(JourneyRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/{value}")
  public Response<Journey> get(JourneyId id) {
    var res = repository.findById(id);
    if (!res.isPresent()) {
      return Response.makeResponse(false, "id does not exist", Optional.empty());
    }
    return Response.makeResponse(res.get());
  }

  @PostMapping("/{value}")
  public Response<Empty> post(JourneyId id) {
    // repository.save(id)
    return Response.makeResponse(true, "", null);
  }

  @PutMapping("/{value}")
  public String put(JourneyId id) {
    return id.value;
  }

}
