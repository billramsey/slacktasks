package com.example.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.MessageByLocaleService;
import com.example.Commands.Arguments;
import com.example.Commands.Command;
import com.example.Commands.CommandService;
import com.example.outgoing.SlackRequest;
import com.example.outgoing.SlackResponse;


@RestController
public class SlackIncomingController {
  //Tower
  //Board

  @Autowired
  MessageByLocaleService messageByLocaleService;

  @Autowired
  CommandService commandService;


  @RequestMapping("/hi")
  public String index() {
    return "Greetings from Spring Boot!";
  }


  @RequestMapping(value="/slack", method=RequestMethod.POST, consumes = {"application/x-www-form-urlencoded"})
  public @ResponseBody String getSlackRequest(@ModelAttribute SlackRequest s) {
    System.out.println(s.toString());

    String text = s.getText();
    Arguments args = new Arguments(text);
    Command command = commandService.findCommand(args.getCommand());

    if (command == null) {
      String invalidText = 
          messageByLocaleService.getMessage("command.not.exist", args.getCommand());
      return new SlackResponse(invalidText).toJSONString();
    }

    if (!command.areArgsGood(args)) {   
      String invalidText = 
          messageByLocaleService.getMessage("command.invalid", command.usage());
      return new SlackResponse(invalidText).toJSONString();
    }

    SlackResponse slackResponse = command.execute(s, args);
    System.out.println("SlackResponse: " + slackResponse.toJSONString());
    return slackResponse.toJSONString();
  }


}