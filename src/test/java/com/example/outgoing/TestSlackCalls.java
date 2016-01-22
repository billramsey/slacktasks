package com.example.outgoing;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.TestSlackApplication;

import flowctrl.integration.slack.SlackClientFactory;
import flowctrl.integration.slack.type.User;
import flowctrl.integration.slack.webapi.SlackWebApiClient;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TestSlackApplication.class)
@EnableAutoConfiguration
@ComponentScan
public class TestSlackCalls {
  @Value("${slacktoken}")
  private String token;
  
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
