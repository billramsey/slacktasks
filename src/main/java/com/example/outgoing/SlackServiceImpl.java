package com.example.outgoing;

import com.example.db.Assignee;
import flowctrl.integration.slack.SlackClientFactory;
import flowctrl.integration.slack.type.User;
import flowctrl.integration.slack.webapi.SlackWebApiClient;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SlackServiceImpl implements SlackService {

  private static SlackWebApiClient webApiClient;

  
  public SlackServiceImpl() {
    String token = "xoxp-12080698544-12076686227-17152273637-9380ca8b3c";
    webApiClient = SlackClientFactory.createWebApiClient(token);
  }

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
