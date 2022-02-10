package com.cs7cs3.JourneySharing.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.cs7cs3.JourneySharing.db.JourneyRepository;
import com.cs7cs3.JourneySharing.entities.Journey;
import com.cs7cs3.JourneySharing.entities.Location;
import com.cs7cs3.JourneySharing.entities.Response;
import com.cs7cs3.JourneySharing.entities.base.Empty;
import com.cs7cs3.JourneySharing.entities.request.CreateJourneyRequest;
import com.cs7cs3.JourneySharing.entities.request.Request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/journey")
public class JourneyController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private JourneyRepository repository;

  @GetMapping("/{id}")
  public Response<Journey> getById(@PathVariable("id") String id, @RequestParam("token") String token) {
    if (/* var token = req.token; validate(token) */ false) {
      logger.error("?");
      return Response.makeResponse(false, "?", "", Optional.empty());
    }

    var res = repository.findById(id);
    if (!res.isPresent()) {
      return Response.makeResponse(false, "id does not exist", /* next_token(token) */ "", Optional.empty());
    }

    return Response.makeResponse(/* next_token(token) */ "", res.get());
  }

  @GetMapping
  public Response<List<Journey>> getByLocation(@RequestParam("token") String token,
      @RequestParam("from_latitude") long from_latitude, @RequestParam("from_longitude") long from_longitude,
      @RequestParam("to_latitude") long to_latitude, @RequestParam("to_longitude") long to_longitude) {
    if (/* var token = req.token; validate(token) */ false) {
      logger.error("?");
      return Response.makeResponse(false, "?", "", Optional.empty());
    }

    Location from = new Location(), to = new Location();
    from.latitude = from_latitude;
    from.longitude = from_longitude;
    to.latitude = to_latitude;
    to.longitude = to_longitude;

    var res = repository.findAvailableJourneys();
    if (res.isEmpty()) {
      return Response.makeResponse(false, "no journey available", /* next_token(token) */ "", Optional.empty());
    }

    res.sort(new Comparator<Journey>() {
      public int compare(Journey a, Journey b) {
        double diffFrom = from.Distance(a.from) - from.Distance(b.from);
        double diffTo = to.Distance(a.to) - to.Distance(b.to);

        // 1 longitude = ? m
        return (int) Math.round((diffFrom + diffTo));
      }
    });

    var _res = new ArrayList<Journey>();
    for (int i = 0; i < Math.min(res.size(), 10); i++) {
      _res.add(res.get(i));
    }

    return Response.makeResponse(/* next_token(token) */ "", _res);
  }

  @PostMapping("/create")
  public Response<Journey> post(@RequestBody Request<CreateJourneyRequest> req) {
    logger.info(req.toString());
    if (!req.validate()) {
      logger.error("?");
      return Response.makeResponse(false, "?", "", Optional.empty());
    }

    // empty payload, early reject
    if (!req.payload.isPresent()) {
      logger.error("?");
      return Response.makeResponse(false, "?", "", Optional.empty());
    }

    if (/* var token = req.token; validate(token) */ false) {
      logger.error("?");
      return Response.makeResponse(false, "?", "", Optional.empty());
    }

    // schema validation
    if (!req.payload.get().validate()) {
      logger.error("?");
      return Response.makeResponse(false, "?", "", Optional.empty());
    }

    var payload = req.payload.get();
    var journey = Journey.make(payload.userId, payload.from, payload.to);

    repository.save(journey);

    return Response.makeResponse(true, "", /* next_token(token) */ "", journey);
  }

  @PutMapping
  public Response<Empty> put(@RequestBody Request<Journey> req) {
    logger.info(req.toString());
    if (!req.validate()) {
      logger.error("?");
      return Response.makeResponse(false, "?", "", Optional.empty());
    }

    // empty payload, early reject
    if (!req.payload.isPresent()) {
      logger.error("?");
      return Response.makeResponse(false, "?", "", Optional.empty());
    }

    if (/* var token = req.token; validate(token) */ false) {
      logger.error("?");
      return Response.makeResponse(false, "?", "", Optional.empty());
    }

    // schema validation
    if (!req.payload.get().validate()) {
      logger.error("?");
      return Response.makeResponse(false, "?", "", Optional.empty());
    }

    repository.save(req.payload.get());
    return Response.makeResponse(true, "", /* next_token(token) */ "", Optional.empty());
  }

}
