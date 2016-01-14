package com.example.Commands;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandService {
  @Autowired
  List<Command> commands;
  
  public List<Command> getCommands() {
    return commands;
  }
  
  public Command findCommand(String name) {
    for (Command c : commands) {
      if (c.name().equals(name)) {
        return c;
      }
    }
    return null;
  }
  

}
