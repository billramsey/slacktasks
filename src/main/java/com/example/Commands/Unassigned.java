package com.example.Commands;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.GlobalVariables;
import com.example.MessageByLocaleService;
import com.example.db.Assignee;
import com.example.db.DatabaseService;
import com.example.db.Project;
import com.example.db.Task;
import com.example.outgoing.SlackRequest;
import com.example.outgoing.SlackResponse;


@Component
public class Unassigned extends Command  {
  
  @Autowired
  DatabaseService databaseService;
  
  @Autowired
  MessageByLocaleService messageByLocaleService;

  
  public String name() {
    return "unassigned";
  }

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
    
    StringBuilder sb = new StringBuilder();
    for(Task t : taskList) {
      sb.append(t.getTitle());
    }
    
    return new SlackResponse(sb.toString());  
  }


}
