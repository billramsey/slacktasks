package com.example.Commands;

import org.springframework.stereotype.Component;

import com.example.GlobalVariables;
import com.example.outgoing.SlackRequest;
import com.example.outgoing.SlackResponse;



@Component
public class ShowBoard extends Command {
  @Override
  public String name() {
    return "showboard";
  }

  @Override
  public String usage() {
    return "/" + GlobalVariables.commandName() + " " 
        +  messageByLocaleService.getMessage("showboard.usage");
  }

  @Override
  public boolean areArgsGood(Arguments args) {
    return true;
  }

  @Override
  public SlackResponse execute(SlackRequest slackRequest, Arguments args) {
    // TODO Auto-generated method stub
    System.out.println("Showing board to the channel");
    return new SlackResponse("Showing board to the channel");
  }

}
