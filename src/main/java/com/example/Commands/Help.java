package com.example.Commands;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.GlobalVariables;
import com.example.MessageByLocaleService;
import com.example.db.DatabaseService;
import com.example.outgoing.SlackRequest;
import com.example.outgoing.SlackResponse;
import com.example.outgoing.SlackService;


@Component
public class Help extends Command  {

  @Autowired
  DatabaseService databaseService;

  @Autowired
  MessageByLocaleService messageByLocaleService;

  @Autowired
  CommandService commandService;

  @Autowired
  SlackService slackService;

  @Override
  public String name() {
    return "help";
  }

  @Override
  public String usage() {
    return "/" + GlobalVariables.commandName() + " " 
        +  messageByLocaleService.getMessage("help.usage");
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
    System.out.println("help [commandName]");

    if (args.getArgs().length == 1) {

      Command command = commandService.findCommand(args.getArgs()[0]);

      if (command == null) {
        String invalidText = 
            messageByLocaleService.getMessage("command.not.exist", args.getCommand());
        return new SlackResponse(invalidText);
      }
      return new SlackResponse(command.usage());  
    }

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(messageByLocaleService.getMessage("help.available.commands"));

    //The autowire of commands doesn't include current.
    //stringBuilder.append("\n" + usage());

    for (Command command : commandService.getCommands()) {
      stringBuilder.append("\n" + command.usage());
    }

    return new SlackResponse(stringBuilder.toString());  
  }


}
