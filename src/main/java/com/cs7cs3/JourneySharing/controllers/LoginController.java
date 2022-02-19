package com.cs7cs3.JourneySharing.controllers;

import com.cs7cs3.JourneySharing.db.AccountRepository;
import com.cs7cs3.JourneySharing.entities.base.Empty;
import com.cs7cs3.JourneySharing.entities.request.LoginRequest;
import com.cs7cs3.JourneySharing.entities.response.Response;
import com.cs7cs3.JourneySharing.utils.Utils;

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
  private AccountRepository accountRepository;

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @PostMapping
  @ResponseBody
  public Response<Empty> login(@RequestBody LoginRequest req) {
    logger.info(req.toString());
    if (!req.validate()) {
      return Response.makeError("request validation failed");
    }

    boolean ret = accountRepository.testPassword(req.username, req.password) > 0;
    if (!ret) {
      return Response.makeError("wrong username or password");
    }

    return Response.makeResponse(Utils.makeToken(req.username));
  }

}
