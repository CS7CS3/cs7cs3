package com.cs7cs3.JourneySharing.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.cs7cs3.JourneySharing.db.JourneyRepository;
import com.cs7cs3.JourneySharing.entities.Journey;
import com.cs7cs3.JourneySharing.entities.Location;
import com.cs7cs3.JourneySharing.entities.base.Empty;
import com.cs7cs3.JourneySharing.entities.request.*;
import com.cs7cs3.JourneySharing.entities.response.Response;
import com.cs7cs3.JourneySharing.utils.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/journey")
public class JourneyController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private JourneyRepository journeyRepository;

  @GetMapping("/{id}")
  public Response<Journey> getById(@PathVariable("id") String id, @RequestParam("token") String token) {
    if (Utils.validateToken(token)) {
      return Response.makeError("token validation failed");
    }

    var res = journeyRepository.findById(id);
    if (!res.isPresent()) {
      return Response.makeError("journey does not exist");
    }

    return Response.makeResponse(Utils.nextToken(token), res.get());
  }

  @GetMapping
  public Response<List<Journey>> getByLocation(@RequestParam("token") String token,
      @RequestParam("from_latitude") long from_latitude, @RequestParam("from_longitude") long from_longitude,
      @RequestParam("to_latitude") long to_latitude, @RequestParam("to_longitude") long to_longitude) {

    if (!Utils.validateToken(token)) {
      return Response.makeError("token validation failed");
    }

    Location from = new Location(), to = new Location();
    from.latitude = from_latitude;
    from.longitude = from_longitude;
    to.latitude = to_latitude;
    to.longitude = to_longitude;

    var res = journeyRepository.findAvailableJourneys();
    if (res.isEmpty()) {
      return Response.makeError("no journey available");
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

    return Response.makeResponse(Utils.nextToken(token), _res);
  }

  @PostMapping("/create")
  public Response<Journey> createJourney(@RequestBody Request<CreateJourneyRequest> req) {
    logger.info(req.toString());

    if (!req.validate()) {
      return Response.makeError("request validation failed");
    }

    if (!req.payload.isPresent()) {
      return Response.makeError("empty payload");
    }

    if (!Utils.validateToken(req.token)) {
      return Response.makeError("token validation failed");
    }

    var payload = req.payload.get();

    if (!payload.validate()) {
      return Response.makeError("payload schema validation failed");
    }

    var journey = Journey.make(payload);
    try {
      journeyRepository.save(journey);
    } catch (DataIntegrityViolationException e) {
      return Response.makeError("user id does not exist");
    }

    return Response.makeResponse(Utils.nextToken(req.token), journey);
  }

  @PostMapping("/join")
  public Response<Journey> updateJourneyStatus(@RequestBody Request<JoinJourneyRequest> req) {
    logger.info(req.toString());

    if (!req.validate()) {
      return Response.makeError("request validation failed");
    }

    if (!req.payload.isPresent()) {
      return Response.makeError("empty payload");
    }

    if (!Utils.validateToken(req.token)) {
      return Response.makeError("token validation failed");
    }

    var payload = req.payload.get();

    if (!payload.validate()) {
      return Response.makeError("payload schema validation failed");
    }

    try {
      if (!(journeyRepository.join(payload.journeyId, payload.userId) > 0)) {
        return Response.makeError("unknown error");
      }
    } catch (DataIntegrityViolationException e) {
      return Response.makeError("journey/user id inconsistency");
    }

    var journey = journeyRepository.findById(payload.journeyId);
    return Response.makeResponse(Utils.nextToken(req.token), journey);
  }

  @PostMapping("/exit")
  public Response<Empty> exitJourneyStatus(@RequestBody Request<ExitJourneyRequest> req) {
    logger.info(req.toString());

    if (!req.validate()) {
      return Response.makeError("request validation failed");
    }

    if (!Utils.validateToken(req.token)) {
      return Response.makeError("token validation failed");
    }

    if (!req.payload.isPresent()) {
      return Response.makeError("empty payload");
    }

    var payload = req.payload.get();

    if (!payload.validate()) {
      return Response.makeError("payload schema validation failed");
    }

    try {
      if (!(journeyRepository.exit(payload.userId) > 0)) {
        return Response.makeError("unknown error");
      }
    } catch (DataIntegrityViolationException e) {
      return Response.makeError("journey/user id inconsistency");
    }

    return Response.makeResponse(Utils.nextToken(req.token));
  }

  @PostMapping("{id}/update1")
  // id: journeyId
  @Transactional
  public Response<Empty> updateJourneyStatus1(@PathVariable("id") String id,  @RequestBody Request<Empty> req){
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

    var check_endtime = journeyRepository.findById(id);
    long time1 = check_endtime.get().createdTime;
    var time3 = System.currentTimeMillis() / 1000;
    var s = check_endtime.get().status;
    if (time1 +1200 < time3 && !s.equals(Journey.JourneyStatus.End)) {
      check_endtime.get().status = Journey.JourneyStatus.End;
      journeyRepository.save(check_endtime.get());
      System.out.println("Update");
      return Response.makeResponse("token");
    }
    else {
      System.out.println("F");
    }
    return Response.makeResponse("token");
  }

  @PostMapping("{id}/update2")
  @Transactional
  //id:journey id
  public Response<Empty> updateJourneyStatus2(@PathVariable("id") String id,  @RequestBody Request<JourneyStatusRequest> req) {

    var payload = req.payload.get();
    var res = journeyRepository.findById(id);
    var journey = res.get();
    var userId = payload.userId;

    for (int i = 0; i < journey.members.size(); i++) {
      var journeyMember = journey.members.get(i);
      var u = journeyMember.userId;
      if( u.equals(userId)){
//        journeyMember.status = Journey.MemberStatus.Arrived;
//        journeyRepository.save(journey);
        journeyRepository.update1(userId);

      }
    }

    for (int i = 0; i < journey.members.size(); i++) {
      var journeyMember = journey.members.get(i);
      var s = journeyMember.status;
      if (!s.equals(Journey.MemberStatus.Arrived)){
        System.out.println(Journey.MemberStatus.Arrived);
        System.out.println(s);
        System.out.println("F");
        break;
      }
      else {
        journey.status = Journey.JourneyStatus.End;
        System.out.println("End");
      }
    }
    journeyRepository.save(journey);

    return Response.makeResponse("token");
  }


}
