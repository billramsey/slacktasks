package com.example.outgoing;

import com.example.db.Assignee;
import org.springframework.stereotype.Service;


@Service
public interface SlackService {
  public String getUserIdByUserName(String userName);
  public Assignee slackUserToAssignee(String userId);
}
