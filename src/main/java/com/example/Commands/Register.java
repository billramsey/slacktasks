package com.example.Commands;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.GlobalVariables;
import com.example.MessageByLocaleService;
import com.example.db.Assignee;
import com.example.db.DatabaseService;
import com.example.db.Project;
import com.example.outgoing.SlackRequest;
import com.example.outgoing.SlackResponse;
import com.example.outgoing.SlackService;


@Component
public class Register extends Command  {
  
  @Autowired
  DatabaseService databaseService;
  
  @Autowired
  MessageByLocaleService messageByLocaleService;

  @Autowired
  SlackService slackService;
  
  public String name() {
    return "register";
  }

  public String usage() {
    return "/" + GlobalVariables.commandName() + " " 
        +  messageByLocaleService.getMessage("register.usage");
  }

  @Override
  public boolean areArgsGood(Arguments args) {
    if (args.getArgs().length != 1) {
      return false;
    }
    return true;
  }
  
  @Override
  public SlackResponse execute(SlackRequest slackRequest, Arguments args) {
    System.out.println("register new user");
    String channelId = slackRequest.getChannel_id();
    
    String userName = args.getArgs()[0];

    Assignee assignee = databaseService.getAssignee(userName);
    if (assignee == null) {
      String userId = slackService.getUserIdByUserName(userName);
      if (userId == null || userId.equals("")) {
        return new SlackResponse(messageByLocaleService.getMessage("user.not.exist"));
      }
      assignee = databaseService.createAssignee(slackService.slackUserToAssignee(userId));
    }
    
    Project project = databaseService.getProject(channelId);
    
    if (project == null) {
      return new SlackResponse(messageByLocaleService.getMessage("project.not.defined"));
    }

    databaseService.assignUserToProject(assignee, project);
    
    return new SlackResponse(messageByLocaleService.getMessage("project.assigned.user.success"));  
  }


}
