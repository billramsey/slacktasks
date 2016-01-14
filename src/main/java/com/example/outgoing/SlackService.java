package com.example.outgoing;

import org.springframework.stereotype.Service;

import com.example.db.Assignee;


@Service
public interface SlackService {
  public String getUserIdByUserName(String userName);
  public Assignee slackUserToAssignee(String userId);
}
