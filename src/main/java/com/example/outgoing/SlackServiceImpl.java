package com.example.outgoing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.example.db.Assignee;

import flowctrl.integration.slack.SlackClientFactory;
import flowctrl.integration.slack.type.User;
import flowctrl.integration.slack.webapi.SlackWebApiClient;

@Service
@Configuration
@PropertySource(value = {"application.properties", "application-secret.properties"}, ignoreResourceNotFound = true)
public class SlackServiceImpl implements SlackService {

  private static SlackWebApiClient webApiClient;
  
  public SlackServiceImpl () {}
  
  @Autowired
  public SlackServiceImpl(String token) {
    webApiClient = SlackClientFactory.createWebApiClient(token);
  }

  @Override
  public String getUserIdByUserName(String userName) {
    List<User> users = webApiClient.getUserList();
    for (User u : users) {
      //System.out.println("n:" + u.getName());
      if(u.getName().equals(userName)) {
        return u.getId();
      }
    }
    return "";
  }

  @Override
  public Assignee slackUserToAssignee(String userId) {
    User user = webApiClient.getUserInfo(userId);

    if (user == null) {
      return null;
    }

    Assignee assignee = new Assignee();

    assignee.setId(userId);
    assignee.setUserName(user.getName());

    return assignee;
  }

}
