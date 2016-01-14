package com.example.Commands;


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
import com.example.outgoing.SlackService;
 

@Component
public class Assign extends Command  {
  
  @Autowired
  DatabaseService databaseService;
   
  @Autowired
  MessageByLocaleService messageByLocaleService;

  @Autowired
  SlackService slackService;
  
  public String name() {
    return "assign";
  }

  public String usage() {
    return "/" + GlobalVariables.commandName() + " " 
        +  messageByLocaleService.getMessage("assign.usage");
  }

  @Override
  public boolean areArgsGood(Arguments args) {
    if (args.getArgs().length != 2) {
      return false;
    }
    return true;
  }
  
  @Override
  public SlackResponse execute(SlackRequest slackRequest, Arguments args) {
    System.out.println("assign [taskId] [userId]");
    String channelId = slackRequest.getChannel_id();
    
    String taskId = args.getArgs()[0];
    String userName = args.getArgs()[1];

    Project project = databaseService.getProject(channelId);
    
    if (project == null) {
      return new SlackResponse(
          messageByLocaleService.getMessage("project.not.defined"));
    }
    
    
    Assignee assignee = databaseService.getAssignee(userName);
    if (assignee == null) {
      return new SlackResponse(
          messageByLocaleService.getMessage("user.not.exist"));
    }
    if (!databaseService.doesProjectHaveAssignee(assignee, project)) {
      return new SlackResponse(
          messageByLocaleService.getMessage("user.not.assigned.to.channel", 
              assignee.getUserName(), project.getChannelId()));
    }
    
    Task task = databaseService.findByTaskId(taskId);
    
    if (task.getAssignee() != null) {
      return new SlackResponse(messageByLocaleService.getMessage("task.already.assigned", 
          task.getAssignee().getUserName()));
    }
     
    databaseService.addAssigneeToTask(task, assignee);
    //databaseService.assignUserToProject(assignee, project);
    
    return new SlackResponse(messageByLocaleService.getMessage("task.assigned.user.success"));  
  }


}
