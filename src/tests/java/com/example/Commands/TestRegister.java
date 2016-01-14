package com.example.Commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.example.MessageByLocaleService;
import com.example.TestSlackApplication;
import com.example.db.Assignee;
import com.example.db.DatabaseService;
import com.example.db.Project;
import com.example.db.Task;
import com.example.db.mongodb.AssigneeRepository;
import com.example.db.mongodb.ProjectRepository;
import com.example.db.mongodb.TaskRepository;
import com.example.outgoing.SlackRequest;
import com.example.outgoing.SlackResponse;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TestSlackApplication.class)
@EnableAutoConfiguration
@ComponentScan
public class TestRegister {
  @Autowired
  CommandService commandService;

  @Autowired
  MessageByLocaleService messageByLocaleService;
  
  @Autowired
  private AssigneeRepository assigneeRepository;
  @Autowired
  private ProjectRepository projectRepository;
  @Autowired
  private TaskRepository taskRepository;
  @Autowired
  DatabaseService databaseService;

  
  //Test projects, assignee, and task
  Project testProject;
  Assignee testAssignee;
  Task testTask;
  
  @Before
  public void setup() {
    assigneeRepository.deleteAll();
    projectRepository.deleteAll();
    taskRepository.deleteAll();
    
    
    testProject = new Project("test-project-id", "test-project-name");
    projectRepository.save(testProject);
    testAssignee = new Assignee("test-assignee-id", "test-assignee-name");
    assigneeRepository.save(testAssignee);
    testTask = new Task("test-task-id", "test-task-title", "test-task-description");
    taskRepository.save(testTask);
  }

  @Test
  public void testRegisterCommandSuccess() {
    SlackRequest slackRequest = new SlackRequest();
    slackRequest.setChannel_id("test-project-id");

    Arguments arg = new Arguments("register test-assignee-name");
    Command command = commandService.findCommand(arg.getCommand());
    
    SlackResponse response = command.execute(slackRequest, arg);
    String responseText = response.getText();
    assertEquals(messageByLocaleService.getMessage("project.assigned.user.success"),
        responseText);
  }
  
  @Test
  public void testRegisterCommandNoSuchUser() {
    SlackRequest slackRequest = new SlackRequest();
    slackRequest.setChannel_id("test-project-id");

    Arguments arg = new Arguments("register blah");
    Command command = commandService.findCommand(arg.getCommand());
    
    SlackResponse response = command.execute(slackRequest, arg);
    String responseText = response.getText();
    assertEquals(messageByLocaleService.getMessage("user.not.exist"),
        responseText);
  }
  
  @Test
  public void testRegisterCommandProjectNotDefined() {
    SlackRequest slackRequest = new SlackRequest();
    slackRequest.setChannel_id("bad channel id");

    Arguments arg = new Arguments("register test-assignee-name");
    Command command = commandService.findCommand(arg.getCommand());
    
    SlackResponse response = command.execute(slackRequest, arg);
    String responseText = response.getText();
    assertEquals(messageByLocaleService.getMessage("project.not.defined"),
        responseText);
  }
}
