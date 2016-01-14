package com.example.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



import java.util.List;

@RestController
public class SlackIncomingController {
  //Tower
  //Board
  
  @RequestMapping("/hi")
  public String index() {
      return "Greetings from Spring Boot!";
  }
/*
  @RequestMapping(value="/", method=RequestMethod.POST, 
        consumes = {"application/x-www-form-urlencoded"})
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

    return slackResponse.toJSONString();
  }
  */

}