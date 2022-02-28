package com.cs7cs3.JourneySharing.entities.messages.journey;

import com.cs7cs3.JourneySharing.entities.messages.Request;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GetJourneyByIdTest {

  private static Logger logger = LoggerFactory.getLogger(GetJourneyByIdTest.class);
  private static ObjectMapper mapper = new ObjectMapper()
      .registerModule((new JacksonAutoConfiguration()).jsonComponentModule());

  @Test
  public void GetJourneyByIdRequest() throws JsonProcessingException {
    var req = Request.make("token", 0, new GetJourneyByIdRequest());

    String result = mapper.writeValueAsString(req);
    logger.error(result);
  }
}
