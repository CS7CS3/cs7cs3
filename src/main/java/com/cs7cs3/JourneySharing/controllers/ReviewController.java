package com.cs7cs3.JourneySharing.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewController {

  @GetMapping
  public String get() {

    return "";
  }

  @PostMapping
  public String post() {

    return "";
  }

  @PutMapping
  public String put() {

    return "";
  }

}
