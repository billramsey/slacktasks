package com.example.Commands;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.GlobalVariables;
import com.example.MessageByLocaleService;
import com.example.db.DatabaseService;
import com.example.db.Project;
import com.example.outgoing.SlackRequest;
import com.example.outgoing.SlackResponse;
import com.example.outgoing.SlackService;

@Component
public class ConfigureChannel extends Command  {

  @Autowired
  DatabaseService databaseService;

  @Autowired
  MessageByLocaleService messageByLocaleService;

  @Autowired
  SlackService slackService;


  @Override
  public String name() {
    return "configurechannel";
  }

  @Override
  public String usage() {
    return "/" + GlobalVariables.commandName() + " " 
        +  messageByLocaleService.getMessage("configure.channel.usage");
  }

  @Override
  public boolean areArgsGood(Arguments args) {
    if (args.getArgs().length > 0) {
      return false;
    }
    return true;
  }

  @Override
  public SlackResponse execute(SlackRequest slackRequest, Arguments args) {
    System.out.println("configurechannel");

    String channelId = slackRequest.getChannel_id();
    Project project = databaseService.getProject(channelId);
    if (project != null) {
      System.out.println("already");
      return new SlackResponse(messageByLocaleService.getMessage("project.already.exists"));
    }
    String channelName = slackRequest.getChannel_name();

    System.out.println("channelName:" + channelName);

    project = databaseService.createProject(channelId, channelName);

    if (project == null) {
      return new SlackResponse(messageByLocaleService.getMessage("unknown.error"));
    }
    return new SlackResponse(messageByLocaleService.getMessage("project.create.success"));  
  }


}
