package com.example.Commands;

import static org.junit.Assert.assertNotNull;

import java.util.List;

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


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TestSlackApplication.class)
@EnableAutoConfiguration
@ComponentScan
public class TestHelp {
  @Autowired
  CommandService commandService;
  @Autowired
  List<Command> commands;
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
  public void testHelpCommand() {
    Arguments arg = new Arguments("help");
    Command command = commandService.findCommand(arg.getCommand());
    assertNotNull(command);
    SlackResponse response = command.execute(new SlackRequest(), arg);

    String responseText = response.getText();
    //System.out.println("responseText: " + responseText);

    for (Command c : commands) {
      Assert.assertThat(responseText, CoreMatchers.containsString(c.usage()));
    }
  }

  @Test
  public void testHelpRegisterCommand() {
    Arguments arg = new Arguments("help register");
    Command command = commandService.findCommand(arg.getCommand());
    assertNotNull(command);
    SlackResponse response = command.execute(new SlackRequest(), arg);

    String responseText = response.getText();
    //System.out.println("responseText: " + responseText);

    Command registerCommand = commandService.findCommand("register");

    Assert.assertThat(responseText, 
        CoreMatchers.containsString(registerCommand.usage()));
  }



}
