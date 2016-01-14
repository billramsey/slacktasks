package com.example.Commands;

import org.springframework.stereotype.Component;

import com.example.GlobalVariables;
import com.example.outgoing.SlackRequest;
import com.example.outgoing.SlackResponse;

@Component
public class Board extends Command {

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
    return new SlackResponse("Returning board personally");
  }

}
