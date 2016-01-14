package com.example.outgoing;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import flowctrl.integration.slack.SlackClientFactory;
import flowctrl.integration.slack.type.User;
import flowctrl.integration.slack.webapi.SlackWebApiClient;

public class TestSlackCalls {

  private String token = "xoxp-12080698544-12076686227-17152273637-9380ca8b3c";
  private SlackWebApiClient webApiClient;

  @Before
  public void setup() {
    webApiClient = SlackClientFactory.createWebApiClient(token);
  }


  @Test
  public void userTest() {
    //webApiClient.setPresenceUser(Presence.AUTO);
    //webApiClient.setActiveUser();
    List<User> users = webApiClient.getUserList();
    for (User u : users) {
      System.out.println(u.getName());
    }
    /*
      User user = users.get(0);
      user = webApiClient.getUserInfo(user.getId());

      UserPresence userPresence = webApiClient.getUserPresence(user.getId());
      Assert.assertTrue(userPresence.getPresence() != null);
     */

  }

}
