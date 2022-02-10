package com.cs7cs3.JourneySharing.controllers;

import java.util.Optional;

import com.cs7cs3.JourneySharing.db.AccountRepository;
import com.cs7cs3.JourneySharing.entities.Response;
import com.cs7cs3.JourneySharing.entities.base.Empty;
import com.cs7cs3.JourneySharing.entities.request.LoginRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

  @Autowired
  private AccountRepository repository;
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @PostMapping
  @ResponseBody
  public Response<Empty> PostHandler(@RequestBody LoginRequest req) {
    logger.info(req.toString());
    if (!req.validate()) {
      logger.error("?");
      return Response.makeResponse(false, "?", "", Optional.empty());
    }

    var res = repository.findById(req.userId);
    if (!res.isPresent()) {
      logger.error("?");
      return Response.makeResponse(false, "id does not exist", "", Optional.empty());
    }

    if (!res.get().password.equals(req.password)) {
      logger.error("?");
      return Response.makeResponse(false, "?", "", Optional.empty());
    }

    return Response.makeResponse("token");
  }

}
