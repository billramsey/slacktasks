package com.example.Commands;

import org.apache.commons.lang.text.StrTokenizer;

import java.util.Arrays;

public class Arguments {
  public String command;
  public String[] args;
  
  public Arguments(String fullTextArgs) {
    if (fullTextArgs.trim().equals("")) {
      return;
    }

    StrTokenizer input = new StrTokenizer(fullTextArgs, ' ', '"');
    String[] textArgs = input.getTokenArray();

    this.command = textArgs[0];
    this.args = Arrays.copyOfRange(textArgs, 1, textArgs.length);
  }
  
  public String getCommand() {
    return command;
  }
  
  public void setCommand(String command) {
    this.command = command;
  }
  
  public String[] getArgs() {
    return args;
  }
  
  public void setArgs(String[] args) {
    this.args = args;
  }

}
