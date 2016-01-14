package com.example.Commands;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.GlobalVariables;
import com.example.MessageByLocaleService;
import com.example.db.DatabaseService;
import com.example.db.Project;
import com.example.db.Task;
import com.example.outgoing.SlackRequest;
import com.example.outgoing.SlackResponse;

import flowctrl.integration.slack.type.Attachment;
import flowctrl.integration.slack.type.Field;


@Component
public class AddTask extends Command  {

  @Autowired
  DatabaseService databaseService;

  @Autowired
  MessageByLocaleService messageByLocaleService;


  @Override
  public String name() {
    return "addtask";
  }

  @Override
  public String usage() {
    return "/" + GlobalVariables.commandName() + " " 
        +  messageByLocaleService.getMessage("addtask.usage");
  }

  @Override
  public boolean areArgsGood(Arguments args) {
    if (args.getArgs().length != 3) {
      return false;
    }
    return true;
  }

  @Override
  public SlackResponse execute(SlackRequest slackRequest, Arguments args) {
    System.out.println("addtask");
    String channelId = slackRequest.getChannel_id();

    Project project = databaseService.getProject(channelId);

    String[] argsArray = args.getArgs();
    Task task = new Task();
    task.setProject(project);
    
    Task t = databaseService.createTask(argsArray[0], argsArray[1], argsArray[2], project, null);
    
    
    return new SlackResponse(messageByLocaleService.getMessage("addtask.success")); 
  }


}
