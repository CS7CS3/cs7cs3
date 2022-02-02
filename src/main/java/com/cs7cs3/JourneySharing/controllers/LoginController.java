package com.cs7cs3.JourneySharing.controllers;

import com.cs7cs3.JourneySharing.entities.Journey;
import com.cs7cs3.JourneySharing.entities.base.UserId;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

  @GetMapping()
  @ResponseBody
  public Journey GetHandler() {
    Journey j = new Journey();
    j.guestIds.add(new UserId());
    return j;
  }

  @PostMapping()
  public String PostHandler() {
    return "Hello World!";
  }

}
