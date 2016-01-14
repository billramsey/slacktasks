package com.example.outgoing;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.db.Assignee;

@Service
public class SlackServiceStub implements SlackService {

  private final Logger logger = LoggerFactory.getLogger(SlackServiceStub.class);

  public SlackServiceStub() { }

  @Override
  public String getUserIdByUserName(String userName) {
    logger.info("Stubbing return for user:" + userName);
    if (userName.equals("billramsey")) {
      return "U0C28L66P";
    }
    return "";
  }
  @Override
  public Assignee slackUserToAssignee(String userId) {
    Assignee assignee = new Assignee();
    assignee.setId(userId);
    assignee.setUserName("fooser");
    return assignee;
  }
}
