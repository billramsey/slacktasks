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
public class Unassigned extends Command  {

  @Autowired
  DatabaseService databaseService;

  @Autowired
  MessageByLocaleService messageByLocaleService;


  @Override
  public String name() {
    return "unassigned";
  }

  @Override
  public String usage() {
    return "/" + GlobalVariables.commandName() + " " 
        +  messageByLocaleService.getMessage("unassigned.usage");
  }

  @Override
  public boolean areArgsGood(Arguments args) {
    if (args.getArgs().length > 1) {
      return false;
    }
    return true;
  }

  @Override
  public SlackResponse execute(SlackRequest slackRequest, Arguments args) {
    System.out.println("unassigned");
    String channelId = slackRequest.getChannel_id();

    Project project = databaseService.getProject(channelId);



    List<Task> taskList = databaseService.getUnassignedTasks(project);

    if (taskList == null || taskList.size() == 0) {
      return new SlackResponse(messageByLocaleService.getMessage("unassigned.none")); 
    }
    
    Attachment attachment = new Attachment();


    for(Task t : taskList) {
      attachment.addField(new Field("id", t.getTaskId()));
      attachment.addField(new Field("title", t.getTitle()));
      attachment.addField(new Field("desc", t.getDescription()));
    }
    SlackResponse sr = new SlackResponse("Unassigned:");
    sr.addAttachment(attachment);
    
    return sr;  
  }


}
