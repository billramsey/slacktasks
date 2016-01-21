package com.example.Commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.GlobalVariables;
import com.example.db.DatabaseService;
import com.example.outgoing.SlackRequest;
import com.example.outgoing.SlackResponse;

@Component
public class Board extends Command {

  @Autowired
  DatabaseService databaseService;
  
  @Override
  public String name() {
    return "board";
  }

  @Override
  public String usage() {
    return "/" + GlobalVariables.commandName() + " " 
        +  messageByLocaleService.getMessage("board.usage");
  }

  @Override
  public boolean areArgsGood(Arguments args) {
    return true;
  }

  @Override
  public SlackResponse execute(SlackRequest slackRequest, Arguments args)  {
    // TODO Auto-generated method stub
    System.out.println("Returning board personally");

    String timeFrame;
    if(args.getArgs().length == 0) {
      timeFrame = "now";
    } else {
      timeFrame = args.getArgs()[0];
    }
    
    //args.  now | q3 | q32015 | Jan | Mar/2015
    
    
    //if (!checkTimeFrame(timeFrame)) {
      
    //}

    //  March 2016
    // user         open   closed   avg close(days) avg open(days)
    // frank          14        8             3         4
    // bob             1       11             2
    // unassigned      2        0             0
    
    //List<Task> openedInTimeFrame = databaseService.getOpenedDuring(dateRange);
    //List<Task> closedInTimeFrame = databaseService.getClosedDuring(dateRange);
        
    return new SlackResponse("Returning board personally");    
  }

}
