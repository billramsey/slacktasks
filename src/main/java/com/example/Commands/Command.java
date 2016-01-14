package com.example.Commands;

import com.example.MessageByLocaleService;
import com.example.outgoing.SlackRequest;
import com.example.outgoing.SlackResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class Command {
  @Autowired
  MessageByLocaleService messageByLocaleService;
  

  public Command() {
  }
  
  public abstract String name();
  public abstract String usage();
  public abstract boolean areArgsGood(Arguments args);
  public abstract SlackResponse execute(SlackRequest slackRequest, Arguments args);
  

}
