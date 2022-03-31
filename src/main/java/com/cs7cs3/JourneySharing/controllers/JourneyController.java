package com.cs7cs3.JourneySharing.controllers;

import java.util.ArrayList;
import java.util.Comparator;

import com.cs7cs3.JourneySharing.db.JourneyRepository;
import com.cs7cs3.JourneySharing.db.UserInfoRepository;
import com.cs7cs3.JourneySharing.entities.Journey;
import com.cs7cs3.JourneySharing.entities.JourneyStatus;
import com.cs7cs3.JourneySharing.entities.UserStatus;
import com.cs7cs3.JourneySharing.entities.messages.Request;
import com.cs7cs3.JourneySharing.entities.messages.Response;
import com.cs7cs3.JourneySharing.entities.messages.journey.ApproveJoinRequest;
import com.cs7cs3.JourneySharing.entities.messages.journey.ApproveJoinResponse;
import com.cs7cs3.JourneySharing.entities.messages.journey.ConfirmArriveRequest;
import com.cs7cs3.JourneySharing.entities.messages.journey.ConfirmArriveResponse;
import com.cs7cs3.JourneySharing.entities.messages.journey.CreateJourneyRequest;
import com.cs7cs3.JourneySharing.entities.messages.journey.CreateJourneyResponse;
import com.cs7cs3.JourneySharing.entities.messages.journey.ExitJourneyRequest;
import com.cs7cs3.JourneySharing.entities.messages.journey.ExitJourneyResponse;
import com.cs7cs3.JourneySharing.entities.messages.journey.GetJourneyByIdRequest;
import com.cs7cs3.JourneySharing.entities.messages.journey.GetJourneyByIdResponse;
import com.cs7cs3.JourneySharing.entities.messages.journey.GetJourneyByLocationRequest;
import com.cs7cs3.JourneySharing.entities.messages.journey.GetJourneyByLocationResponse;
import com.cs7cs3.JourneySharing.entities.messages.journey.GetUnapprovedUsersRequest;
import com.cs7cs3.JourneySharing.entities.messages.journey.GetUnapprovedUsersResponse;
import com.cs7cs3.JourneySharing.entities.messages.journey.JoinJourneyRequest;
import com.cs7cs3.JourneySharing.entities.messages.journey.JoinJourneyResponse;
import com.cs7cs3.JourneySharing.entities.messages.journey.StartJourneyRequest;
import com.cs7cs3.JourneySharing.entities.messages.journey.StartJourneyResponse;
import com.cs7cs3.JourneySharing.utils.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/journey")
public class JourneyController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private JourneyRepository journeyRepository;

  @Autowired
  private UserInfoRepository userInfoRepository;

  @PostMapping("/approve-join")
  public Response<ApproveJoinResponse> approveJoin(@RequestBody Request<ApproveJoinRequest> req) {
    var res = req.test();
    if (res.right.isPresent()) {
      return Response.makeError(res.right.get());
    }
    var payload = res.left;

    var status = journeyRepository.getUserStatusByUserId(payload.userId);
    if (status != UserStatus.PendingApproval) {
      Response.makeError("user is already in this group");
    }

    journeyRepository.setUserStatus(payload.userId, UserStatus.Waiting.ordinal());

    return Response.make(Utils.nextToken(req.token), ApproveJoinResponse.make());
  }

  @PostMapping("/confirm-arrive")
  public Response<ConfirmArriveResponse> confirmArrive(@RequestBody Request<ConfirmArriveRequest> req) {
    var res = req.testIgnorePayloadCheck();
    if (res.isPresent()) {
      return Response.makeError(res.get());
    }

    var uid = Utils.getIdByToken(req.token);

    var status = journeyRepository.getUserStatusByUserId(uid);
    if (status != UserStatus.Travelling) {
      Response.makeError("user does not in travelling, cannot 'arrive'");
    }

    var journeyId = journeyRepository.getJourneyIdByUserId(uid);

    journeyRepository.setUserStatus(uid, UserStatus.Arrived.ordinal());

    var allUserStatus = journeyRepository.getUserStatusByJourneyId(journeyId);
    boolean allArrived = true;
    for (UserStatus _status : allUserStatus) {
      if (_status != UserStatus.Arrived) {
        allArrived = false;
        break;
      }
    }

    if (allArrived) {
      journeyRepository.setJourneyStatus(journeyId, JourneyStatus.End.ordinal());
      var userIds = journeyRepository.getUserIdByJourneyId(journeyId);
      for (String userId : userIds) {
        userInfoRepository.addHistory(userId, journeyId);
      }
    }

    return Response.make(Utils.nextToken(req.token), ConfirmArriveResponse.make());
  }

  @PostMapping("/create")
  public Response<CreateJourneyResponse> create(@RequestBody Request<CreateJourneyRequest> req) {
    var res = req.test();
    if (res.right.isPresent()) {
      return Response.makeError(res.right.get());
    }
    var payload = res.left;
    var uid = Utils.getIdByToken(req.token);

    var journey = Journey.make(uid, payload);
    try {
      journeyRepository.save(journey);
    } catch (DataIntegrityViolationException e) {
      return Response.makeError("user id does not exist");
    }

    return Response.make(Utils.nextToken(req.token), CreateJourneyResponse.make(journey));
  }

  @PostMapping("/exit")
  public Response<ExitJourneyResponse> exit(@RequestBody Request<ExitJourneyRequest> req) {
    var res = req.test();
    if (res.right.isPresent()) {
      return Response.makeError(res.right.get());
    }
    var payload = res.left;

    JourneyStatus status = journeyRepository.getJourneyStatusByUserId(payload.userId);
    if (status != JourneyStatus.Waiting) {
      return Response.makeError("journey already start, cannot exit");
    }

    try {
      if (!(journeyRepository.exit(payload.userId) > 0)) {
        return Response.makeError("unknown error");
      }
    } catch (DataIntegrityViolationException e) {
      return Response.makeError("journey/user id inconsistency");
    }

    return Response.make(Utils.nextToken(req.token), ExitJourneyResponse.make());
  }

  @PostMapping("/get-by-id")
  public Response<GetJourneyByIdResponse> getById(@RequestBody Request<GetJourneyByIdRequest> req) {
    var testRes = req.test();
    if (testRes.right.isPresent()) {
      return Response.makeError(testRes.right.get());
    }
    var payload = testRes.left;

    var res = journeyRepository.findById(payload.journeyId);
    if (!res.isPresent()) {
      return Response.makeError("journey does not exist");
    }

    return Response.make(Utils.nextToken(req.token), GetJourneyByIdResponse.make(res.get()));
  }

  @PostMapping("/get-by-location")
  public Response<GetJourneyByLocationResponse> getByLocation(@RequestBody Request<GetJourneyByLocationRequest> req) {
    var testRes = req.test();
    if (testRes.right.isPresent()) {
      return Response.makeError(testRes.right.get());
    }
    var payload = testRes.left;

    var res = journeyRepository.findAvailableJourneys();
    if (res.isEmpty()) {
      return Response.makeError("no journey available");
    }

    res.sort(new Comparator<Journey>() {
      public int compare(Journey a, Journey b) {
        double diffFrom = payload.from.Distance(a.from) - payload.from.Distance(b.from);
        double diffTo = payload.to.Distance(a.to) - payload.to.Distance(b.to);

        // 1 longitude = ? m
        return (int) Math.round((diffFrom + diffTo));
      }
    });

    var journeys = new ArrayList<Journey>();
    for (int i = 0; i < Math.min(res.size(), 10); i++) {
      journeys.add(res.get(i));
    }

    return Response.make(Utils.nextToken(req.token), GetJourneyByLocationResponse.make(journeys));
  }

  @PostMapping("/join")
  public Response<JoinJourneyResponse> join(@RequestBody Request<JoinJourneyRequest> req) {
    var res = req.test();
    if (res.right.isPresent()) {
      return Response.makeError(res.right.get());
    }
    var payload = res.left;
    var uid = Utils.getIdByToken(req.token);

    JourneyStatus status = journeyRepository.getJourneyStatusByJourneyId(payload.journeyId);
    if (status == null) {
      return Response.makeError("cannot find journey");
    }

    if (status != JourneyStatus.Waiting) {
      return Response.makeError("journey already start, cannot join");
    }

    try {
      if (!(journeyRepository.join(payload.journeyId, uid, UserStatus.PendingApproval.ordinal()) > 0)) {
        return Response.makeError("unknown error");
      }
    } catch (DataIntegrityViolationException e) {
      return Response.makeError("journey/user id inconsistency");
    }

    var journey = journeyRepository.findById(payload.journeyId);
    if (!journey.isPresent()) {
      return Response.makeError("journey not found");
    }

    return Response.make(Utils.nextToken(req.token), JoinJourneyResponse.make(journey.get()));
  }

  @PostMapping("/start")
  public Response<StartJourneyResponse> start(@RequestBody Request<StartJourneyRequest> req) {
    var res = req.testIgnorePayloadCheck();
    if (res.isPresent()) {
      return Response.makeError(res.get());
    }
    var uid = Utils.getIdByToken(req.token);

    var journeyId = journeyRepository.getJourneyIdByHostId(uid);
    if (journeyId == null) {
      return Response.makeError("cannot find journey");
    }

    var allUserStatus = journeyRepository.getUserStatusByJourneyId(journeyId);
    if (allUserStatus == null) {
      return Response.makeError("cannot get user status");
    }

    boolean noPendingApproval = true;
    for (UserStatus _status : allUserStatus) {
      if (_status == UserStatus.PendingApproval) {
        noPendingApproval = false;
        break;
      }
    }

    if (noPendingApproval) {
      journeyRepository.setJourneyStatus(journeyId, JourneyStatus.Travelling.ordinal());
      journeyRepository.setUserStatusByJourneyId(journeyId, UserStatus.Travelling.ordinal());
    }

    return Response.make(Utils.nextToken(req.token), StartJourneyResponse.make());
  }

  @PostMapping("/get-unapproved")
  public Response<GetUnapprovedUsersResponse> getUnapproved(@RequestBody Request<GetUnapprovedUsersRequest> req) {
    var res = req.testIgnorePayloadCheck();
    if (res.isPresent()) {
      return Response.makeError(res.get());
    }

    var uid = Utils.getIdByToken(req.token);
    var journeyId = journeyRepository.getJourneyIdByHostId(uid);

    var userIds = journeyRepository.getUserIdByJourneyIdAndStatus(journeyId, UserStatus.PendingApproval.ordinal());

    return Response.make(Utils.nextToken(req.token), GetUnapprovedUsersResponse.make(userIds));
  }

}
