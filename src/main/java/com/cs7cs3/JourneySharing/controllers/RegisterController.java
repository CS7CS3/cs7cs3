package com.cs7cs3.JourneySharing.controllers;

import java.util.Optional;

import com.cs7cs3.JourneySharing.db.AccountRepository;
import com.cs7cs3.JourneySharing.entities.Account;
import com.cs7cs3.JourneySharing.entities.RegisterRequest;
import com.cs7cs3.JourneySharing.entities.Response;
import com.cs7cs3.JourneySharing.entities.base.Empty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

  @Autowired
  private AccountRepository repository;
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @PostMapping
  @ResponseBody
  public Response<Empty> PostHandler(@RequestBody RegisterRequest req) {
    logger.info(req.toString());
    if (!req.validate()) {
      logger.error("?");
      return Response.makeResponse(false, "?", "", Optional.empty());
    }

    var res = repository.findById(req.userId);
    if (res.isPresent()) {
      logger.error("?");
      return Response.makeResponse(false, "id does not exist", "", Optional.empty());
    }

    var account = new Account();
    account.uerId = req.userId;
    account.password = req.password;

    repository.save(account);

    return Response.makeResponse("token");
  }

}
